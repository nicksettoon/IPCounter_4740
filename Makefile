DIR=IPCounter

all: # displays this file
	cat ./Makefile

local: #runs the partitioner jar on the local fs and displays results
	make cleanlocal 2> /dev/null
	-gunzip -c access_log.gz > access_log 
	java -jar local_partitioner.jar access_log ${DIR}
	@echo "output is in ${DIR}"
	make outputlocal 2> /dev/null
	
hdfs: # runs the main partitioner job on hdfs and displays results
	make cleanhdfs 2> /dev/null
	-gunzip -c access_log.gz | hdfs dfs -put - weblog/access_log
	@echo "processing log files in weblog"
	hadoop jar hdfs_partitioner.jar ip_counter.ProcessLogs weblog ${DIR}
	@echo "output is in ${DIR}"
	make outputhdfs 2> /dev/null

cleanhdfs: # cleans up previous hdfs partitioner run
	-hdfs dfs -rm -r ${DIR} 2> /dev/null

cleanlocal: #cleans up previous local partitioner run
	-rm -rf ${DIR}
	-rm access_log

outputhdfs: # prints output #11 from previous partitioner run for comparison with assignment sheet
	hdfs dfs -cat ${DIR}/part-r-00011 2> /dev/null | less

outputlocal: # prints output #11 from previous partitioner run for comparison with assignment sheet
	cat output/part-r-00011 2> /dev/null | less
