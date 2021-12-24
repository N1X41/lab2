package LAB2;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.TupleWritable;

import java.io.IOException;

public class FlightJoinMapper extends Mapper<LongWritable, TupleWritable, LongWritable, FloatWritable> {
    public void map(LongWritable key, TupleWritable value, OutputCollector<LongWritable, FloatWritable> output, Reporter reporter) throws IOException {
        LongWritable airportCode = (LongWritable) value.get(10);
        FloatWritable delay = (FloatWritable) value.get(17);
        output.collect(airportCode, delay);
    }
}
