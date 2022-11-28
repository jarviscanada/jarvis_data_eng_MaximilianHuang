# Introduction
```grep``` is a Linux command-line utility used to search text files for specified strings or lines.
This project is a Java program emulating certain behaviors of ```grep```, recursively searching
directories for files that have lines matching a certain regular expression. In addition, there is
an implementation that uses streams instead of lists to fix a performance issue. Technologies used
include Java 8, including the SLF4j and Log4j libraries, Maven, and Docker.

# Quick Start
There are two ways to use the Java Grep app; using the Jar file, or using Docker. To run the Jar
file, download the repository, then run from this directory (grep):
```
mvn clean compile package
regex=...
src_dir=...
out_path=...
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp ${regex} ${src_dir} ${out_path}
```
Where ```regex``` is the string to search for, ```tar_dir``` is the directory containing all files
to be searched, and ```out_path``` is the path to where the output file should be created.

To run from Docker, run the following:
```
regex=...
src_dir=...
out_file=...
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out huangm66/grep ${regex} ${src_dir} ./out/${out_file}
```
Note that the output should instead be specified as a filename.

# Implementation
## Pseudocode
The implementation is based around the ```process``` method, described by the following pseudocode:
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```
* Get files recursively from directory and subdirectories
* Read lines in file
* If regex pattern is matched, add line to list
* Print matched lines to file


## Performance Issue
Attempting to read from a large file without enough memory allocated to the heap can cause the
program to throw an OutOfMemoryError. This problem can be solved by using a Stream to store the
lines of read files so that they can be lazily read while being filtered. This solution is 
implemented in the ```JavaGrepLambdaImp.java``` class.

# Test
The application was manually tested against ```egrep```, using various regex patterns in searches
against a sample text.

# Deployment
Although the grep program can be run by compiling and packaging the code using Maven, running the
app in a dockerized form is much simpler. ```Dockerfile``` is used to build the image along with the
following commands:
```
mvn clean package
docker build -t huangm66/grep .
docker push huangm66/grep
```

# Improvement
Three possible improvements:
* Allow output to stdout instead of only files
* Change regex pattern matching to not require matching whole line; currently requires adding 
  ```.*``` to start and end of desired string
* Add line number, containing file