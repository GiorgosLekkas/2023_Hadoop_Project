import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper1 extends Mapper <LongWritable, Text, Text, Text> {

	private final static IntWritable one = new IntWritable(1);
	Text textKey = new Text();

	public void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
			
		String line = value.toString();
		String[] field1 = line.split("\"");
		String[] field2 = line.split(",");
						
		if(field1.length>2) {
				
			field1[1] = field1[1].replace("[", "");
			field1[1] = field1[1].replace("]", "");
			field1[1] = field1[1].replace("{", "");
			field1[1] = field1[1].replace("product_id", "");
			field1[1] = field1[1].replace("quantity", "");
			field1[1] = field1[1].replace("item_price", "");
			field1[1] = field1[1].replace(" ", "");
			field1[1] = field1[1].replace(":", "");
			field1[1] = field1[1].replace("'", "");
				
			String[] str = field1[1].split("}");
			for(String s:str) {
				int a = 0, b = 0, n = 0;
				String[] tmp = s.split(",");
				if(tmp.length==3) {
					a = Integer.parseInt(tmp[1]);
					b = Integer.parseInt(tmp[2]);
					n = a*b;
					context.write(new Text(tmp[0]), new Text(tmp[1] + "," + String.valueOf(n) + "," + field2[1]));
				}else if(tmp.length==4) {
					a = Integer.parseInt(tmp[2]);
					b = Integer.parseInt(tmp[3]);
					n = a*b;
					context.write(new Text(tmp[1]), new Text(tmp[2] + "," + String.valueOf(n) + "," + field2[1]));
				}
				context.write(new Text("count"), new Text("1"));
			}
				
		}
			
	}
}