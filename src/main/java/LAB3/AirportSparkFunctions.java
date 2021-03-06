package LAB3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class AirportSparkFunctions {
    private static final int AIRPORT_CODE_COLUMN_NUMBER = 0;
    private static final int ORIGIN_AIRPORT_COLUMN_NUMBER = 11;
    private static final int DEST_AIRPORT_COLUMN_NUMBER = 14;
    private static final int DELAY_COLUMN_NUMBER = 18;
    private static final float ZERO = 0.0f;
    private static final boolean ABORTED_FLIGHT_FLAG = true;
    private static final boolean NOT_ABORTED_FLIGHT_FLAG = false;

    public AirportSparkFunctions() {}

    public static Function<Tuple2<LongWritable, Text>, Boolean> removeFirstLine =
            (Function<Tuple2<LongWritable, Text>, Boolean>) e -> e._1.get() != 0;

    public static PairFunction<Tuple2<LongWritable, Text>, Long, String> parseAirportFile =
            (PairFunction<Tuple2<LongWritable, Text>, Long, String>) line -> {
                String[] columns = StringTools.splitWithCommas(line._2.toString());
                long airportCode = Long.parseLong(StringTools.removeQuotes(columns[AIRPORT_CODE_COLUMN_NUMBER]));
                String airportName = StringTools.concatWords(columns, 1, columns.length);

                return new Tuple2<>(airportCode, airportName);
            };

    public static PairFunction<Tuple2<LongWritable, Text>, Tuple2<Long, Long>, FlightData> parseFlightsFile =
            (PairFunction<Tuple2<LongWritable, Text>, Tuple2<Long, Long>, FlightData>) line -> {
                String[] columns = StringTools.splitWithCommas(line._2.toString());
                long originAirportCode = Long.parseLong(StringTools.removeQuotes(columns[ORIGIN_AIRPORT_COLUMN_NUMBER]));
                long destAirportCode = Long.parseLong(StringTools.removeQuotes(columns[DEST_AIRPORT_COLUMN_NUMBER]));
                String delay = columns[DELAY_COLUMN_NUMBER];
                if (!delay.isEmpty()) {
                    return new Tuple2<>(new Tuple2<>(originAirportCode, destAirportCode),
                            new FlightData(Float.parseFloat(delay), NOT_ABORTED_FLIGHT_FLAG));
                }

                return new Tuple2<>(new Tuple2<>(originAirportCode, destAirportCode),
                        new FlightData(ZERO, ABORTED_FLIGHT_FLAG));
            };

    public static Function2<FlightData, FlightData, FlightData> groupByKey =
            (Function2<FlightData, FlightData, FlightData>) (fd1, fd2) -> {
                float d1 = fd1.getDelay(), d2 = fd2.getDelay();
                float newDelay = d1 > d2 ? d1 : d2;
                int afc1 = fd1.getAbortedFlightCount(), afc2 = fd2.getAbortedFlightCount();
                int dfc1 = fd1.getDelayedFlightCount(), dfc2 = fd2.getDelayedFlightCount();
                int fc1 = fd1.getFlightCount(), fc2 = fd2.getFlightCount();

                return new FlightData(newDelay, afc1 + afc2, dfc1 + dfc2, fc1 + fc2);
            };

    public static PairFunction<Tuple2<Tuple2<Long, Long>, FlightData>, String, String>
    getFlightResultData(Broadcast<Map<Long, String>> airportInfoBroadcasted) {
        return (PairFunction<Tuple2<Tuple2<Long, Long>, FlightData>, String, String>) e -> {
            String originName = airportInfoBroadcasted.value().get(e._1._1);
            String destName = airportInfoBroadcasted.value().get(e._1._2);
            String key = originName + " -> " + destName;
            String value = String.format("Delay: %f, Ratio: %.2f%%", e._2.getDelay(), e._2.getRatio());

            return new Tuple2<>(key, value);
        };
    }
}