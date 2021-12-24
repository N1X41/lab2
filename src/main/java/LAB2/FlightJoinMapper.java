package LAB2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.TupleWritable;

import java.io.IOException;

public class FlightJoinMapper extends MapReduceBase implements Mapper<LongWritable, TupleWritable, Text, Text> {
    @Override
    public void map(LongWritable key, TupleWritable value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
        Text airportCode = (Text)value.get(10);
        Text delay = (Text)value.get(17);
        output.collect(airportCode, delay);
    }
}
