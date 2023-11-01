import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer2 extends Reducer<Text, Text, Text, Text> {
	
	ArrayList<Events> list = new ArrayList<Events>();
	int count = 0;
	int i = 0;
		
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		for (Text value : values) {
			if(key.toString().compareTo("count")==0) {
				count++;
			}else {
				String num = value.toString();
				list.add(new Events(key.toString(), Integer.parseInt(num.toString())));
			}
			
		}
		Collections.sort(list,new CompareEvents());
		if(count!=0) {
			for(Events ev:list) {
				
				context.write(new Text(ev.getEvent()), new Text(String.valueOf(ev.getNumber())));
			}
		}
		
	}
	
	
}