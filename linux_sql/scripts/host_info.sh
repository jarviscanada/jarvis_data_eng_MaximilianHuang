psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
  echo "Incorrect number of arguments"
  echo "Usage: host_info.sh host port db_name username password"
  exit 1
fi

lscpu_out=$(lscpu)

hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | grep -E "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | grep -E "Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | grep -E "Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(echo "$lscpu_out" | grep -E "CPU MHz" | awk '{print $3}' | xargs)
L2_cache=$(echo "$lscpu_out" | grep -E "L2 cache" | awk '{print $3}' | sed s/K// | xargs)
total_mem=$(grep -e "MemTotal" < /proc/meminfo | awk '{print $2}' | xargs)
timestamp=$(vmstat -t | grep -o -E "[0-9]{4}-.*:[0-9]{2}" | xargs)

insert_stmt="INSERT INTO host_info
VALUES(DEFAULT, '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$L2_cache', '$total_mem', '$timestamp')"

export PGPASSWORD=$psql_password
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
exit $?