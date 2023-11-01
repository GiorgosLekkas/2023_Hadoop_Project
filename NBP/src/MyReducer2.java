import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class MyReducer2 extends Reducer<Text, Text, Text, Text> {

	ArrayList<Pairs> list = new ArrayList<Pairs>();
	int count = 0;
		
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		
		for (Text value : values) {
			if(key.toString().contains("count")!=true) {
				String[] str = key.toString().split("-");
				if(str.length!=2) {
					return;
				}
				list.add(new Pairs(str[0], str[1], Integer.parseInt(value.toString())));
			}else 
				count += Integer.parseInt(value.toString());
		}
		
		if(count!=0) {
			Collections.sort(list,new ComparePairs());
			for( Pairs p:list) {
				String products = p.getPair();
				int purchases = p.getPurchases();
				context.write(new Text(products),new Text(String.valueOf(purchases)));
			}
		}
	}
}