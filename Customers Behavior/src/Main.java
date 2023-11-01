import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Configuration conf1 = new Configuration();
		Job job1 = Job.getInstance(conf1, "Customer Behaviour");
		job1.setJarByClass(Main.class);
		job1.setMapperClass(MyMapper1.class);
		job1.setReducerClass(MyReducer1.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));
		job1.waitForCompletion(true);
		//System.exit(job1.waitForCompletion(true) ? 0 : 1);
		
		Configuration conf2 = new Configuration();
		Job job2 = Job.getInstance(conf2, "Customer Behaviour");
		job2.setJarByClass(Main.class);
		job2.setMapperClass(MyMapper2.class);
		job2.setReducerClass(MyReducer2.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job2, new Path(args[1] + "/part-r-00000"));
		FileOutputFormat.setOutputPath(job2, new Path(args[2]));
		System.exit(job2.waitForCompletion(true) ? 0 : 1);
		
	}
	
}
