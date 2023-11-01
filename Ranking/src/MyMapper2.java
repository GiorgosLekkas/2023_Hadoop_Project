import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper2 extends Mapper <LongWritable, Text, Text, Text> {

	int i = 0;
	Text textKey = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		
		String line = value.toString();
		String[] field = line.split("\t");
		if(line.contains("count")==false) {
			String[] str = field[1].split(",");
			context.write(new Text(field[0]), new Text( str[0] + "," + str[1] + "," + str[2]));
		}else 
			context.write(new Text("count"), new Text(String.valueOf(1)));
		
	}
}