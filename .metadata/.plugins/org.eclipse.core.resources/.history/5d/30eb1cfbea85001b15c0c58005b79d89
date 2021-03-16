#! /bin/bash

dir="$1"
srcdir="weblog"
jar="partitioner.jar"

## hdfs dfs -mkdir "$dir" 2> /dev/null
gunzip -c access_log.gz | hdfs dfs -put - weblog/access_log

echo "processing log files in $srcdir"
hadoop jar $jar ip_counter.ProcessLogs $srcdir $dir
echo "output is in $dir"