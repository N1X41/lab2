package LAB3;

import org.apache.hadoop.io.LongWritable;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

public class FlightApp {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: FlightApp <input file_flight_info> <input file_airport_info> <output path>");
            System.exit(-1);
        }

        PairFunction<LongWritable, Text> airportNamesKeyData

        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flightInfoRDD = sc.textFile(args[0]);
        JavaRDD<String> airportInfoRDD = sc.textFile(args[1]);
    }

}