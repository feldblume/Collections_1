package vector.vectorContainer;

import vector.Vector;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nataliya on 03.11.14.
 */
public class VectorCollection<T extends Vector> implements Collection {
    private T[] vectors;
    @Override
    public int size() {
        return vectors.length;
    }

    @Override
    public boolean isEmpty() {
        if (vectors.length == 0)
            return true;
        else return false;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i<vectors.length; i++) {
            if(vectors[i].equal((T)o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("No Iterator by now!");
    }

    @Override
    public Object[] toArray() {
//        T[] result = new T[this.vectors.length]();
//        for(int i = 0; i< vectors.length; i++) {
//            Vector temp = new Vector();
//            result[i] = vectors[i].cl   ????
//        }
        return vectors.clone(); //????
    }

    @Override
    public T[] toArray(Object[] a) {
        a = this.vectors.clone();
        return (T[])a; //????
    }

    @Override
    public boolean add(Object o) {
        int l = vectors.length;
        Vector[] newVectors = new Vector[vectors.length + 1]; //?? optimize
        System.arraycopy(vectors,0,newVectors,0,vectors.length);
        newVectors[l-1] = (Vector)o;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = -1;
        for(int i = 0; i< vectors.length; i++) {
            if(vectors[i].equal((Vector)o)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        Vector[] result = new Vector[vectors.length-1];
        int current = 0;
        for(int i = 0; i<vectors.length; i++){
            if(index == i) {
                // skip
            }
            else {
                result[current++] = vectors[i];
            }
        }
        vectors = (T[])result;
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        for(T element : c){
            if(!this.contains(element) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        for(Object element : c) {
            this.add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        for(Object element : c) {
            this.remove(element);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection c) {
        for(int i = 0; i<vectors.length; i++) {
            if(!c.contains(vectors[i])) {
                this.remove(vectors[i]); // may contain error, or may not
            }
        }
    }

    @Override
    public void clear() {
        vectors = null;
    }
}
