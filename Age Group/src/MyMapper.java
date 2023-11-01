import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper <Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	Text textKey = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		String[] field = line.split(",");
		textKey.set(field[6]);
		if(textKey.toString().compareTo("birthdate")!=0) {
			String[] str = textKey.toString().split("-");
			if( str[0].compareTo("2007")==0 || str[0].compareTo("2006")==0 || str[0].compareTo("2005")==0 || str[0].compareTo("2004")==0 || str[0].compareTo("2003")==0 )
				context.write(new Text("15-19 "),one);
			else if ( str[0].compareTo("2002")==0 || str[0].compareTo("2001")==0 || str[0].compareTo("2000")==0 || str[0].compareTo("1999")==0 || str[0].compareTo("1998")==0  )
				context.write(new Text("20-24 "),one);
			else if ( str[0].compareTo("1997")==0 || str[0].compareTo("1996")==0 || str[0].compareTo("1995")==0 || str[0].compareTo("1994")==0 || str[0].compareTo("1993")==0  )
				context.write(new Text("25-29 "),one);
			else if ( str[0].compareTo("1992")==0 || str[0].compareTo("1991")==0 || str[0].compareTo("1990")==0 || str[0].compareTo("1989")==0 || str[0].compareTo("1988")==0  )
				context.write(new Text("30-34 "),one);
			else if ( str[0].compareTo("1987")==0 || str[0].compareTo("1986")==0 || str[0].compareTo("1985")==0 || str[0].compareTo("1984")==0 || str[0].compareTo("1983")==0  )
				context.write(new Text("35-39 "),one);
		}
			
	}
}