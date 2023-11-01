import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyMapper1 extends Mapper <Object, Text, Text, Text> {

	Text textKey = new Text();
	int n = 0;

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
		String line = value.toString();
		String[] field1 = line.split("\"");
		String[] field2 = line.split(",");
						
		if(field1.length>2) {
				
			ArrayList<String> ids = new ArrayList<String>();
				
			field1[1] = field1[1].replace("[", "");
			field1[1] = field1[1].replace("]", "");
			field1[1] = field1[1].replace("{", "");
			field1[1] = field1[1].replace("product_id", "");
			field1[1] = field1[1].replace("quantity", "");
			field1[1] = field1[1].replace("item_price", "");
			field1[1] = field1[1].replace(" ", "");
			field1[1] = field1[1].replace(":", "");
			field1[1] = field1[1].replace("'", "");

			field1[1] = field1[1].replace("}", "");
				
				
			String[] str = field1[1].split(",");
			if(str.length==3)
				return;
			for(int i=0;i<str.length;i=i+3) {
				ids.add(str[i]);
			}
			for(int i = 0;i<ids.size();i++) 
				for(int j = i+1;j<ids.size();j++) {
					int a = Integer.parseInt(ids.get(i));
					int b = Integer.parseInt(ids.get(j));
					if(a<=b)
						context.write(new Text(ids.get(i) + "-" + ids.get(j)), new Text("1"));
					if(a>=b)
						context.write(new Text(ids.get(j) + "-" + ids.get(i)), new Text("1"));
					context.write(new Text("count"),new Text("1"));
				}
		}
			
	}
		
}