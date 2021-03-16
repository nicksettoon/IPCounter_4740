package ip_counter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ip_counter.Month;

public class month_test {

	public static void main(String[] args) {
		
		String logLine = "10.223.157.186 - - [15/Jul/2009:15:50:36 -0700] \"GET /assets/img/dummy/secondary-news-2.jpg HTTP/1.1\" 200 5397\"";
		String month = "";

		String datetimeRegex = "(\\d{2}/.*:\\d{2})\\s-";
		Pattern datetimePattern = Pattern.compile(datetimeRegex); // feed pattern object the regex
		Matcher datetimeMatcher = datetimePattern.matcher(logLine); // feed matcher the search string

		
		// if date and time were found
		if (datetimeMatcher.find()) {
			String datetime = datetimeMatcher.group(1); // get datetime regex result
			System.out.println(datetime);
			/* regex for pulling out month
			 * a / followed by three non-whitespace characters followed by a /
			 */
			String monthRegex = "/(\\w{3})/"; // regex to match
			Pattern monthPattern = Pattern.compile(monthRegex); // create pattern object
			Matcher monthMatcher = monthPattern.matcher(datetime); // run matcher on datetime string
			if (monthMatcher.find())
				month = monthMatcher.group(1); // fill output string with regex result
		}
		System.out.println(month);
	}
}
