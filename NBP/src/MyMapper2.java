import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper2 extends Mapper <Object, Text, Text, Text> {

	public void map(Object key, Text value, Context context) throws IOException,InterruptedException {
		
		String[] str = value.toString().split("\t");
		if(value.toString().contains("count")==false) {
			context.write(new Text(str[0]), new Text(str[1]));
		}else
			context.write(new Text("count"), new Text("1"));
	}
}