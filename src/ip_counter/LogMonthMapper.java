package ip_counter;

//stdlib
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


// hadoop stuff
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @author Nicklaus Settoon
 * mapper that parses a single log file line via regular expressions and outputs a key,value pair.
 * the key is the ip address of the log line
 * the value is the month in which the request was made
 */

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException{
		
		String logLine = value.toString(); // var for line in log file
		String ip = ""; // var for ip address
		String month = ""; // var for month that ip was hit

		/* regex for pulling out IP address.
		* (1-3 digits followed by a .) 3 times in a row, then followed by another set of 1-3 digits
		*/
		String ipRegex = "^(?:\\d{1,3}[.]){3}\\d{1,3}";
		Pattern ipPattern = Pattern.compile(ipRegex); // feed pattern object the regex
		Matcher ipMatcher = ipPattern.matcher(logLine); // feed matcher the search string

		/* regex for pulling out date and time info
		* 2 digits followed by a /, all the way to a : followed by 2 final digits
		*/
		String datetimeRegex = "(\\d{2}/.*:\\d{2})\\s-"; // datetime regex to match
		Pattern datetimePattern = Pattern.compile(datetimeRegex); // feed pattern object the regex
		Matcher datetimeMatcher = datetimePattern.matcher(logLine); // feed matcher the search string
		
		// if ip was found
		if (ipMatcher.find())
			ip = ipMatcher.group();
		else // print error
		{
			System.err.println("No IP address found in log line: " + key);
			return;
		}
		
		// if date and time were found
		if (datetimeMatcher.find()) {
			String datetime = datetimeMatcher.group(1); // get datetime regex result
			/* regex for pulling out month
			 * a / followed by three non-whitespace characters followed by a /
			 */
			String monthRegex = "/(\\w{3})/"; // regex to match
			Pattern monthPattern = Pattern.compile(monthRegex); // create pattern object
			Matcher monthMatcher = monthPattern.matcher(datetime); // run matcher on datetime string
			if (monthMatcher.find())
				month = monthMatcher.group(1); // fill output string with regex result
			else
			{
				System.err.println("Could not get month from datetime string" + key);
				return;
			}
		}
		else // print error
		{
			System.err.println("No datetime info in log line: " + key);
			return;
		}

//		System.out.println("ip: " + ip + "\nmonth: " + month);

		// write result to output
		context.write(new Text(ip), new Text(month));
	}

}
