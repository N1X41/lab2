package LAB2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightJoinMapper extends Mapper<LongWritable, Text, IDKey, Text> {
    private static final int AIRPORT_CODE_COLUMN_NUMBER = 14;
    private static final int DELAY_COLUMN_NUMBER = 18;
    private static final boolean FLIGHT_FLAG = true;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (!key.equals(new LongWritable(0))) {
            String[] columns = StringTools.splitWithCommas(value.toString());
            Integer airportId = Integer.parseInt(StringTools.removeQuotes(columns[AIRPORT_CODE_COLUMN_NUMBER]));
            String delay = columns[DELAY_COLUMN_NUMBER];
            if (!delay.isEmpty()) {
                context.write(new IDKey(airportId, FLIGHT_FLAG), new Text(delay));
            }
        }
    }
}