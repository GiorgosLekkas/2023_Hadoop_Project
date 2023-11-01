import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer1 extends Reducer<Text, Text, Text, Text> {
	int i = 0;
		
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
			
		int sum_q = 0, sum_p = 0, sum = 0;
		ArrayList<String> id = new ArrayList<String>();
			
		if(key.toString().compareTo("count")!=0) {
			for (Text value : values) {
				String a = value.toString();
				String[] str = a.split(",");
				sum_q +=  Integer.parseInt(str[0]);
				sum_p +=  Integer.parseInt(str[1]);
				if(id.contains(str[2])==false)
					id.add(str[2]);
			}
			context.write(key, new Text(String.valueOf(sum_q) + "," + String.valueOf(sum_p) + "," + String.valueOf(id.size())));	
		}else {
			for (Text value : values) {
				sum += Integer.parseInt(value.toString());
			}
			context.write(key, new Text(String.valueOf(sum)));
		}
	}
}