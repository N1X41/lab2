package LAB2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IDKey implements WritableComparable<IDKey> {
    private Integer airportId;
    private Boolean isFlight;
    private String data;

    public IDKey() {
        super();
    }

    public IDKey(Integer airportId, Boolean isFlight, String data) {
        super();
        this.airportId = airportId;
        this.isFlight = isFlight;
        this.data = data;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public String getData() {
        return data;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(airportId);
        out.writeBoolean(isFlight);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        airportId = in.readInt();
        isFlight = in.readBoolean();
    }

    @Override
    public int compareTo(IDKey obj) {
        int c = this.airportId.compareTo(obj.airportId);
        if (c == 0) {
            Boolean b = new Boolean(false);
        }
    }
}
