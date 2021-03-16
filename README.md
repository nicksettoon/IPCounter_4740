# IPCounter_4740
Analyzes an Apache log file and generates 12 files, one for each month. In each file, there is a list of IP addresses with the number of times each IP was hit.

Required: Hadoop MapReduce, Hadoop HDFS, Java 7

$ make : View the following make targets with their commands and descriptions.

$ make local
	
$ make hdfs

$ make cleanhdfs

$ make cleanlocal

$ make outputhdfs

$ make outputlocal
