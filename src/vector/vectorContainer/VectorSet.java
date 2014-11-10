package vector.vectorContainer;

import vector.Vector;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Jack on 11/10/14.
 */

public class VectorSet<V extends Vector> extends VectorCollection<V> implements Set {
    @Override
    public boolean add(Object o) {
        if(this.contains(o)) {
            return false;
        } else {
            this.add(o);
            return true;
        }
    }
}
