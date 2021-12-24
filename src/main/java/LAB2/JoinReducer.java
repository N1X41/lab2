package LAB2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class JoinReducer extends Reducer<IDKey, String, Text, Text>{
    @Override
    protected void reduce(IDKey key, Iterable<String> values, Context context)
}