package LAB2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IDKey implements WritableComparable<IDKey> {
    private Integer airportId;
    private Text data;

    public IDKey(Integer airportId, Text data) {
        super();
        this.airportId = airportId;
        this.data = data;
    }
}
