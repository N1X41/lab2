package LAB2;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.TupleWritable;

import java.io.IOException;

public class FlightJoinMapper extends Mapper<LongWritable, TupleWritable, IDKey, FloatWritable> {
    private static final int AIRPORT_CODE_COLUMN_NUMBER = 10;
    private static final int DELAY_COLUMN_NUMBER = 17;

    public void map(LongWritable key, TupleWritable value, OutputCollector<LongWritable, IDKey> output, Reporter reporter) throws IOException {
        IDKey airportCode = (LongWritable) value.get(AIRPORT_CODE_COLUMN_NUMBER);
        FloatWritable delay = (FloatWritable) value.get(DELAY_COLUMN_NUMBER);
        output.collect(airportCode, delay);
    }
}
