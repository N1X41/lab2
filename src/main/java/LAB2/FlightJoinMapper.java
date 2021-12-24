package LAB2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.join.TupleWritable;

import java.io.IOException;

public class FlightJoinMapper extends Mapper<LongWritable, Text, IDKey, Text> {
    private static final int AIRPORT_CODE_COLUMN_NUMBER = 10;
    private static final int DELAY_COLUMN_NUMBER = 17;

    
}
