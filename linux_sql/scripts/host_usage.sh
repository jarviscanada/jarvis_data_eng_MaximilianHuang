psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
  echo "Incorrect number of arguments"
  echo "Usage: host_usage.sh host port db_name username password"
  exit 1
fi

vmstat_out=$(vmstat --unit M | tail -n 1)

hostname=$(hostname -f)
memory_free=$(echo "$vmstat_out" | awk '{print $4}' | xargs)
cpu_idle=$(echo "$vmstat_out" | awk '{print $15}' | xargs)
cpu_kernel=$(echo "$vmstat_out" | awk '{print $14}' | xargs)
disk_io=$(vmstat -d | tail -n 1 | awk '{print $10}' | xargs)
disk_available=$(df -BM / | tail -n 1 | awk '{print $4}' | sed 's/M//' | xargs)

timestamp=$(vmstat -t | grep -o -E "[0-9]{4}-.*:[0-9]{2}" | xargs)
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

insert_stmt="INSERT INTO host_usage
VALUES('$timestamp', $host_id, '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"

export PGPASSWORD=$psql_password
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?