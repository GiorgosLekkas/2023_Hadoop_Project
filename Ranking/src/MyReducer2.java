import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer2 extends Reducer<Text, Text, Text, Text> {

	ArrayList<Product> list = new ArrayList<Product>();
	int count = 0;
		
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int sum_q = 0, sum_p = 0, sum_c = 0;
		int j = 0;
		
		if(key.toString().compareTo("count")!=0) {
			for (Text value : values) {
				String a = value.toString();
				if(a.contains("count")==false) {
					String[] str = a.split(",");
					if(str.length!=3) {
						return;
					}
					sum_q = Integer.parseInt(str[0]);
					sum_p = Integer.parseInt(str[1]);
					sum_c = Integer.parseInt(str[2]);
				}else
					count ++;
				
			}
			list.add(new Product(String.valueOf(key.toString()), String.valueOf(sum_q), String.valueOf(sum_p), String.valueOf(sum_c)));
			Collections.sort(list,new CompareProducts());
		}else {
			for (Text value : values) {
				count += Integer.parseInt(value.toString());
			}
		}
		if(count!=0) {
			int i = 0;
			
			for( Product p:list) {
				String id = p.getId();
				String quantity = p.getQuantity();
				String av_price = p.getAvprice();
				String customers = p.getCustomers();
				context.write(new Text(String.valueOf(++i) + " " + id + " " + quantity + " " + customers + " " + av_price),new Text(""));
			}
		}
	}
}