import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer1 extends Reducer<Text, Text, Text, Text> {
		
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		int sum = 0;		
		ArrayList<String> ids = new ArrayList<String>();
		
		for (Text value : values) {
			String id = value.toString();
			if(ids.contains(id)==false)
				ids.add(id);
		}
		sum = ids.size();
		context.write(key, new Text(String.valueOf(sum)));
		
	}
	
	
}