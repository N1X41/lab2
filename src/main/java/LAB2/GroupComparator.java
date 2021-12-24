package LAB2;

import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableComparable;

public class GroupComparator extends WritableComparator {
    public GroupComparator() {
        super(IDKey.class, true);
    }

    @Override
    public int compare(WritableComparable wc1, WritableComparable wc2){
        IDKey ke1 = (IDKey)wc1;
        IDKey ke2 = (IDKey)wc2;

        return 0;
    }
}
