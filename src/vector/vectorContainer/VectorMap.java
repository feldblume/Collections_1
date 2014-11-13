package vector.vectorContainer;

import java.util.*;

import vector.Vector;

/**
 * Created by Jack on 11/10/14.
 */
public class VectorMap<K extends String ,V extends Vector> implements Map<K,V> {
    Object[] keys;
    Object[] values;
    int size;
    int allocatedSize = 32;

    public VectorMap() {
        keys = new Object[allocatedSize];
        values = new Object[allocatedSize];
        size = 0;
    }

    /**
     * Map to String, also showing map size and allocated size;
     * @return Report string
     */
    @Override
    public String toString() {
        String s = "";
//        for(int i = 0; i< size(); i++) {
//            s += "key: " + keys[i] + ", value: " + values[i].toString() + " \n";
//        }
        return "Array of size: " + size + ", allocated: " + allocatedSize + "\n" + s;
    }

    /**
     * Sets new sizes of keys and values arrays arrays
     * @param newSize new sizes
     */
    private void setSize(int newSize) {
        keys = reallocateArrayIfNecessary(keys, newSize);
        values = reallocateArrayIfNecessary(values, newSize);
        size = newSize;

    }

    /**
     * Reallocates arrays if this is required by newSize param. Extends arrays twice for expansion.
     * @param arr Object array
     * @param newSize New size of array
     * @return Reallocated array
     */
    private Object[] reallocateArrayIfNecessary(Object[] arr, int newSize) {
        if(newSize > allocatedSize) { // expansion is needed
            int tempSize = allocatedSize;
            while(tempSize < newSize) {
                tempSize *= 2;
            }
            allocatedSize = tempSize;
            return reallocateArray(arr, tempSize);
        } else if(newSize < allocatedSize/2 && newSize >= 32) { // decrease is desirable, but greater than 32
            int tempSize = allocatedSize;
            while(tempSize >= newSize*2) {
                tempSize /= 2;
            }
            allocatedSize = tempSize;
            return reallocateArray(arr, tempSize);
        } else {
            return arr;
        }
    }

    /**
     * Reallocates given array to have newAllocatedSize
     * @param array Array param
     * @param newAllocatedSize Size to be reallocated to
     * @return Reallocated array
     */
    private Object[] reallocateArray(Object[] array, int newAllocatedSize) {
        Object[] nArray = new Object[newAllocatedSize];
        if(newAllocatedSize >array.length) {
            System.arraycopy(array,0,nArray,0,array.length);
        } else if(newAllocatedSize <array.length) {
            System.arraycopy(array,0,nArray,0, newAllocatedSize);
        } else {
            nArray = array;
        }
        return nArray;
    }

    /**
     * Checks whether a key is of valid format
     * @param o input object
     * @return returns true if object is of valid type
     */
    private boolean checkKeyValidity(Object o) {
        if(!(o instanceof String)) {
            throw new ClassCastException("Attempting to use invalid key type");
        }
        return true;
    }
    /**
     * Checks whether a value is of valid format
     * @param o input object
     * @return returns true if object is of valid type
     */
    private boolean checkValueValidity(Object o) {
        if(!(o instanceof Vector)) {
            throw new ClassCastException("Attempting to use invalid value type");
        }
        return true;
    }

    public VectorMap(Map m) {
        // through collection export
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        checkKeyValidity(key);
        for(int i = 0; i<size(); i++) {
            if(keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        checkValueValidity(value);
        for(int i = 0; i<size(); i++) {
            if(values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        checkKeyValidity(key);
        for(int i = 0; i<size(); i++) {
            if(keys[i].equals(key)) {
                return (V)values[i];
            }
        }
        return null;
    }

    // returns the previous value associated with key, or null if there was no mapping for key
    @Override
    public V put(K key, V value) {
        checkKeyValidity(key);
        checkValueValidity(value);
        for(int i=0; i<size(); i++) {
            K current = (K)keys[i];
            if(current.equals(key)){
                V tmp = (V)values[i];
                values[i] = value;
                return tmp;
            }
        }
        setSize(size()+1);
        keys[size()] = key;
        values[size()] = value;
        return null;
    }

    @Override
    public V remove(Object key) {
        return null; // ??
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
