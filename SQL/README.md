##### Set up tables
    CREATE TABLE cd.members
    (
       memid integer NOT NULL, 
       surname character varying(200) NOT NULL, 
       firstname character varying(200) NOT NULL, 
       address character varying(300) NOT NULL, 
       zipcode integer NOT NULL, 
       telephone character varying(20) NOT NULL, 
       recommendedby integer,
       joindate timestamp NOT NULL,
       CONSTRAINT members_pk PRIMARY KEY (memid),
       CONSTRAINT fk_members_recommendedby FOREIGN KEY (recommendedby)
            REFERENCES cd.members(memid) ON DELETE SET NULL
    );

    CREATE TABLE cd.facilities
    (
       facid integer NOT NULL, 
       name character varying(100) NOT NULL, 
       membercost numeric NOT NULL, 
       guestcost numeric NOT NULL, 
       initialoutlay numeric NOT NULL, 
       monthlymaintenance numeric NOT NULL, 
       CONSTRAINT facilities_pk PRIMARY KEY (facid)
    );

    CREATE TABLE cd.bookings
    (
       bookid integer NOT NULL, 
       facid integer NOT NULL, 
       memid integer NOT NULL, 
       starttime timestamp NOT NULL,
       slots integer NOT NULL,
       CONSTRAINT bookings_pk PRIMARY KEY (bookid),
       CONSTRAINT fk_bookings_facid FOREIGN KEY (facid) REFERENCES cd.facilities(facid),
       CONSTRAINT fk_bookings_memid FOREIGN KEY (memid) REFERENCES cd.members(memid)
    );

### Modifying Data
##### Insert some data into a table
    INSERT INTO cd.facilities
    VALUES
        (9, 'Spa', 20, 30, 100000, 800);

##### Insert calculated data into a table
    INSERT INTO cd.facilities
    VALUES
        (
            (
                SELECT
                MAX(facid)
                from
                cd.facilities
            ) + 1,
            'Spa',
            20,
            30,
            100000,
            800
        );

##### Update some existing data
    UPDATE
        cd.facilities
    SET
        initialoutlay = 10000
    WHERE
        facid = 1;

##### Update a row based on the contents of another row
    UPDATE
        cd.facilities
    SET
        membercost = facs2.membercost * 1.1,
        guestcost = facs2.guestcost * 1.1
    FROM
        (
            SELECT
                membercost,
                guestcost
            FROM
                cd.facilities
            WHERE
                facid = 0
        ) facs2
    WHERE
        facid = 1;

##### Delete all bookings
    DELETE FROM 
        cd.bookings;

##### Delete a member from the cd.members table 
    DELETE FROM
        cd.members
    WHERE
        memid = 37;

### Basics (misc)
##### Classify results into buckets
    SELECT
        name,
        CASE WHEN (monthlymaintenance > 100) THEN 'expensive' ELSE 'cheap' END AS cost
    FROM
        cd.facilities;

##### Combining results from multiple queries
    SELECT
        surname
    FROM
        cd.members
    UNION
    SELECT
        name
    FROM
        cd.facilities;

### Joins
##### Retrieve the start times of members' bookings
    SELECT
        b.starttime
    FROM
        cd.members AS m,
        cd.bookings AS b
    WHERE
        m.memid = b.memid
        AND m.firstname = 'David'
        AND m.surname = 'Farrell';

##### Work out the start times of bookings for tennis courts
    SELECT
        b.starttime,
        f.name
    FROM
        cd.bookings AS b,
        cd.facilities AS f
    WHERE
        b.facid = f.facid
        AND f.name LIKE 'Tennis%'
        AND b.starttime >= '2012-09-21'
        AND b.starttime < '2012-09-22';

##### Produce a list of all members, along with their recommender
    SELECT
        m1.firstname AS memfname,
        m1.surname AS memsname,
        m2.firstname AS recfname,
        m2.surname AS rescname
    FROM
        cd.members AS m1
    LEFT JOIN cd.members AS m2 ON m1.recommendedby = m2.memid
    ORDER BY
        memsname,
        memfname;

##### Produce a list of all members who have recommended another member
    SELECT
        m1.firstname AS memfname,
        m1.surname AS memsname,
        m2.firstname AS recfname,
        m2.surname AS rescname
    FROM
        cd.members AS m1
        LEFT JOIN cd.members AS m2 ON m1.recommendedby = m2.memid
    ORDER BY
        memsname,
        memfname;

##### Produce a list of all members, along with their recommender, using no joins
    SELECT
        DISTINCT mems.firstname || ' ' || mems.surname as member,
        (
            SELECT
                recs.firstname || ' ' || recs.surname AS recommender
            FROM
                cd.members recs
            WHERE
                recs.memid = mems.recommendedby
        )
    FROM
        cd.members mems
    ORDER BY
        member;

### Aggregation
##### Count the number of recommendations each member makes
    SELECT
        recommendedby,
        COUNT(*)
    FROM
        cd.members
    WHERE
        recommendedby IS NOT NULL
    GROUP BY
        recommendedby
    ORDER BY
        recommendedby;

##### List the total slots booked per facility
    SELECT
        facid,
        SUM(slots) as "Total Slots"
    FROM
        cd.bookings
    GROUP BY
        facid
    ORDER BY
        facid;

##### List the total slots booked per facility in a given month
    SELECT
        facid,
        SUM(slots) as "Total Slots"
    FROM
        cd.bookings
    WHERE
        starttime >= '2012-09-01'
        and starttime < '2012-10-01'
    GROUP BY
        facid
    ORDER BY
        SUM(slots);

##### List the total slots booked per facility per month
    SELECT
        facid,
        EXTRACT (
            MONTH
            FROM
            starttime
        ) as "month",
        SUM(slots) as "Total Slots"
    FROM
        cd.bookings
    WHERE
        EXTRACT (
            YEAR
            FROM
                starttime
        ) = 2012
    GROUP BY
        facid,
        month
    ORDER BY
        facid,
        month;

##### Find the count of members who have made at least one booking
    SELECT
        COUNT(DISTINCT memid)
    FROM
        cd.bookings;

##### List each member's first booking after September 1st 2012
    SELECT 
        DISTINCT surname,
        firstname,
        b.memid,
        MIN(starttime)
    FROM
        cd.members m,
        cd.bookings b
    WHERE
        m.memid = b.memid
        AND starttime > '2012-09-01'
    GROUP BY
        surname,
        firstname,
        b.memid
    ORDER BY
        b.memid;

##### Produce a list of member names, with each row containing the total member count 
    SELECT
        COUNT(*) over(),
        firstname,
        surname
    FROM
        cd.members
    ORDER BY
        joindate;

##### Produce a numbered list of members
    SELECT
        ROW_NUMBER() OVER(
            ORDER BY
            joindate
        ),
        firstname,
        surname
    FROM
        cd.members;

##### Output the facility id that has the highest number of slots booked, again
    SELECT
        facid,
        total
    FROM
        (
            SELECT
                facid,
                sum(slots) total,
                rank() over (
                    order by
                        sum(slots) desc
                )
            FROM
                cd.bookings
            GROUP BY
                facid
        ) ranking
    WHERE
        rank = 1;

### Strings
##### Format the names of members
    SELECT
        surname || ', ' || firstname AS name
    FROM
        cd.members;

##### Perform a case-insensitive search
    SELECT
        *
    FROM
        cd.facilities
    WHERE
        UPPER(name) LIKE 'TENNIS%'; `

##### Find telephone numbers with parentheses
    SELECT
        memid,
        telephone
    FROM
        cd.members
    WHERE
        telephone ~ '\(';

##### Count the number of members whose surname starts with each letter of the alphabet 
    SELECT
        (
            substr(surname, 0, 2)
        ) letter,
        COUNT(*)
    FROM
        cd.members
    GROUP BY
        letter
    ORDER BY
        letter;

## More topics
##### How to create an index
Use CREATE INDEX syntax:
```
CREATE INDEX index_name
ON table_name (col1, col2, ...);
```

##### What are the advantages/disadvantages of an index?
Indexes help retrieve specific rows from a table by providing a better way to search for them. This makes SELECT operation faster at the cost of slowing down UPDATE, DELETE, and INSERT because of the overhead required to maintain the index when the table is modified. They also require space in storage and RAM.

##### What are different kind of indexes?
PostgreSQL has several types of indexes:
- B-tree index, the default index type
- Hash index, can only be used if equality condition is used in query
- GiST (generalized search tree) index, which are considered when b-tree index cannot be used
- SP-GiST (space partitioned GiST) index, supporting non-balanced data structures such as quadtrees, k-d trees, and radix trees
- GIN (generalized inverted index) are used for multiple component values like arrays
- BRIN (block range index) stores summaries about values stored in consecutive physical block ranges of a table. For data types that have a linear sort order, the indexed data corresponds to the minimum and maximum values of the values in the column for each block range

##### How to show execution plan?
Use EXPLAIN then the statement you wish to see the execution plan of

##### What's a full scan?
A full table scan is a scan made on a table, where each row is read in a sequential order. One situation in which a full scan would be used is when no index exists for the table.

##### How do you optimize a query? 
One way to find ways to optimize a query is to use EXPLAIN ANALYZE to check if performance estimations of queries matches their actual performance. If the scan is doing a full scan when it doesn't need to, changing the query or adding an index might help performance.
