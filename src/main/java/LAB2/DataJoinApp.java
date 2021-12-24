package LAB2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;

import java.io.IOException;

public class DataJoinApp {
    public static void main(String[] args) throws IOException{
        if (args.length != 3) {
            System.err.println("Usage: DataJoinApp <input file_1> <input file_2> <output path>");
            System.exit(-1);
        }


    }
}
