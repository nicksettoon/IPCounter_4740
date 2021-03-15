package ip_counter;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountReducer extends Reducer<Text, Text, Text, IntWritable>{

	@Override
	public void reduce(Text _ip, Iterable<Text> _months, Context context)
		throws IOException, InterruptedException {
		
		int count = 0;
		
		for (Text month : _months)
			count++;
		
		context.write(_ip, new IntWritable(count));
	}
}
