package LAB2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class AirportJoinMapper extends Mapper<LongWritable, Text, IDKey, Text> {
    private static final int AIRPORT_CODE_COLUMN_NUMBER = 0;
    private static final boolean AIRPORT_FLAG = false;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (!key.equals(new LongWritable(0))) {
            String[] columns = StringTools.splitWithCommas(value.toString());
            Integer airportCode = Integer.parseInt(StringTools.removeQuotes(columns[AIRPORT_CODE_COLUMN_NUMBER]));
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < columns.length; i++) {
                builder.append(columns[i]);
            }
            String name = StringTools.removeQuotes(builder.toString());
            context.write(new IDKey(airportCode, AIRPORT_FLAG), new Text(name));
        }
    }
}
