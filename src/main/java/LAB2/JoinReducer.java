package LAB2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<IDKey, String, Text, Text>{
    @Override
    protected void reduce(IDKey key, Iterable<String> values, Context context) throws IOException, InterruptedException{
        Iterator<String>
    }
}