package dataexpo;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ArrivalDelayMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private final static IntWritable one = new IntWritable(1);
	private Text outkey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		Airline al = new Airline(value);
		if(al.isArriveDelayAvailable() && al.getArriveDelayTime() > 0) {
			outkey.set(al.getYear() +"-" + al.getMonth());
			context.write(outkey, one);
		}
	}
	
	
}
