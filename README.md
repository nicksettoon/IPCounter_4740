# IPCounter_4740
Analyzes an Apache log file and generates 12 files, one for each month. In each file, there is a list of IP addresses with the number of times each IP was hit.

Required:
	Hadoop MapReduce, Hadoop HDFS, Java 7
	A file titled access_log.gz containing a valid Apache HTTP log file

This application is intended to be run with the make targets provided in the Makefile for ease of use.

Since I was developing the assignment running on the local filesystem, but the labs were mostly done on HDFS, I made two separate jars, one for running on the local filesystem (local_partitioner.jar) and the other for running on HDFS (hdfs_partitioner.jar)



$ make : View the following make targets with their commands and descriptions.

$ make local
	
$ make hdfs

$ make cleanhdfs

$ make cleanlocal

$ make outputhdfs

$ make outputlocal
