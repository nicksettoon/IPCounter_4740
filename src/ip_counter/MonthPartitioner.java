package ip_counter;

// internal imports
import ip_counter.Month;

// hadoop imports
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MonthPartitioner<K2, V2> extends Partitioner<Text, Text> {

	@Override
	public int getPartition(Text _ip, Text _month, int numReduceTasks) {
		try {
			return Month.valueOf(_month.toString()).getMonth();
		} catch (Exception IllegalArgumentException) {
			System.out.println("Month given to partitioner did not exist.");
		}
		return 0;
	}
}
