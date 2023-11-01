import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper1 extends Mapper <Object, Text, Text, Text> {

	private final static IntWritable one = new IntWritable(1);
	Text textKey = new Text();
	int n = 0;

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
		String line = value.toString();
		String[] field = line.split(",");
			
		if(field.length>1) 
			if(line.contains("event_name")!=true)
				context.write(new Text(field[1]), new Text(field[0]));
				
	}
			
}