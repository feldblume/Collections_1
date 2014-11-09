package vector.vectorContainer;

import vector.Vector;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Jack on 11/9/14.
 */
public class VectorList<V extends Vector> extends VectorCollection<V> implements List {

    @Override
    public boolean addAll(int index, Collection c) {
        int colSize = c.size();
        if(c.size()==0)
            return false;
        V[] nVectors = (V[]) Array.newInstance(vectors.getClass().getComponentType(),vectors.length+colSize);
        System.arraycopy(vectors,0,nVectors,0,vectors.length);
        System.arraycopy(c.toArray(),0,nVectors,vectors.length,colSize);
        return true;
    }

    @Override
    public Object get(int index) {
        return vectors[index];
    }

    @Override
    public Object set(int index, Object element) {
        V oldVector = vectors[index];
        vectors[index] = (V)element;
        return oldVector;
    }

    @Override
    public void add(int index, Object element) {
        V[] nVectors = (V[]) Array.newInstance(vectors.getClass().getComponentType(),vectors.length+1);
        for(int i = 0; i<index; i++){
            nVectors[i] = vectors[i];
        }
        nVectors[index] = (V)element;
        for(int i = index+1; i<nVectors.length; i++) {
            nVectors[i] = vectors[i-1];
        }
        vectors = nVectors;
    }

    @Override
    public Object remove(int index) {
        V[] nVectors = (V[]) Array.newInstance(vectors.getClass().getComponentType(),vectors.length-1);
        for(int i = 0; i<index; i++) {
            nVectors[i] = vectors[i];
        }
        V deleted = vectors[index];

        for(int i = index+1; i<vectors.length; i++) {
            nVectors[i-1] = vectors[i];
        }
        vectors = nVectors;
        return deleted;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i<vectors.length; i++) {
            if(vectors[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = vectors.length -1; i>=0; i--) {
            if(vectors[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException("Iterator is yet to be defined");
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException("Iterator is yet to be defined");
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        int newSize = toIndex-fromIndex+1;
        V[] nVectors = (V[]) Array.newInstance(vectors.getClass().getComponentType(),newSize);
        for(int i = fromIndex; i<=toIndex; i++){
            nVectors[i-fromIndex] = vectors[i];
        }
        return Arrays.asList(nVectors);
    }
}
