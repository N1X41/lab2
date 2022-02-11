package LAB3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

public class AirportSparkFunctions {
    private static final int AIRPORT_CODE_COLUMN_NUMBER = 0;
    private static final int ORIGIN_AIRPORT_COLUMN_NUMBER = 11;
    private static final int DEST_AIRPORT_COLUMN_NUMBER = 14;
    private static final int DELAY_COLUMN_NUMBER = 18;
    private static final boolean ABORTED_FLIGHT_FLAG = true;
    private static final boolean NOT_ABORTED_FLIGHT_FLAG = false;

    PairFunction<String, LongWritable, Text> airportNamesKeyData = new PairFunction<String, LongWritable, Text>() {
        @Override
        public Tuple2<LongWritable, Text> call(String line) {
            String[] columns = StringTools.splitWithCommas(line);
            LongWritable airportCode = new LongWritable(Integer.parseInt(StringTools.removeQuotes(columns[AIRPORT_CODE_COLUMN_NUMBER])));
            Text airportName = new Text(StringTools.concatWords(columns, 1, columns.length));
            return new Tuple2<>(airportCode, airportName);
        }
    };
}
