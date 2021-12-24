package LAB2;

import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableComparable;

public class GroupComparator extends WritableComparator {
    public GroupComparator() {
        super(IDKey.class, true);
    }

    @Override
    public int compare(WritableComparable){
        
    }
}
