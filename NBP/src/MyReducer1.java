import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer1 extends Reducer<Text, Text, Text, Text> {
	
	int i = 0;
		
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			
		int sum = 0;
		int count = 0;
		String textkey = key.toString();
			
		for (Text value : values) {
			if(textkey.compareTo("count")!=0) {
				String s = value.toString();
				sum += Integer.parseInt(s);
			}else
				count++;
		}
		if(sum>0)
			context.write(new Text(key), new Text(String.valueOf(sum)));
		if(count>0)
			context.write(new Text("count"), new Text(String.valueOf(count)));
	}
}