DIR=IPCounter

all: # displays this file
	cat ./Makefile

partitioner: # runs the main partitioner job and displays results
	-make clean 2> /dev/null
	-./runner.sh ${DIR}
	-make output 2> /dev/null

clean: # cleans up previous partitioner run
	hdfs dfs -rm -r ${DIR} 2> /dev/null

output: # prints output #11 from previous partitioner run for comparison with assignment sheet
	hdfs dfs -cat ${DIR}/part-r-00011 2> /dev/null | less
