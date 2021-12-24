package LAB2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class JoinReducer extends Reducer<IDKey, String, Text, Text>{
    @Override
    protected void reduce(IDKey key, Iterable<String> values, Context context) throws IOException, InterruptedException{
        Iterator<String> iter = values.iterator();
        String name = iter.next();
        float min = Float.MAX_VALUE, max = (float) 0.0, avg = (float) 0.0;
        long count = 0;
        while (iter.hasNext()) {
            float currentTime = Float.parseFloat(iter.next());
            if (currentTime < min) {
                min = currentTime;
            }
            if (currentTime > max) {
                max = currentTime;
            }
            avg = avg * count + currentTime;
            count++;
            avg /= (float) count;
        }
        if (count > 0) {
            String res = "Name: " + name + ", min: " + min + ", max: " + max + ", avg: " + avg;
            context.write(new Text(key.getAirportId().toString()), new Text(res));
        }
    }
}