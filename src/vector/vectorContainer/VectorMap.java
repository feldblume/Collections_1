package vector.vectorContainer;

import java.util.*;

import vector.Vector;

/**
 * Created by Jack on 11/10/14.
 */
public class VectorMap<K extends String ,V extends Vector> implements Map<K,V> {
//    Set<Map.Entry<K,V>> entries;

    Object[] keys;
    Object[] vals;
    int size;
    int allocatedSize = 32;

    public VectorMap() {
        keys = new Object[allocatedSize];
        vals = new Object[allocatedSize];
        size = 0;
    }
    public VectorMap(Map<K,V> source) {
        this();
        this.keys = source.keySet().toArray().clone();
        this.vals = source.values().toArray().clone();
        this.size = source.size();
        int aSize = 32;
        while(aSize<size())
            aSize *= 2;
        allocatedSize = aSize;
    }

    /**
     * Map to String, also showing map size and allocated size;
     * @return Report string
     */
    @Override
    public String toString() {
        String s = "";
        return "Array of size: " + size + ", allocated: " + allocatedSize + "\n" + s;
    }

    /**
     * Sets new sizes of keys and vals arrays arrays
     * @param newSize new sizes
     */
    private void setSize(int newSize) {
        keys = reallocateArrayIfNecessary(keys, newSize);
        vals = reallocateArrayIfNecessary(vals, newSize);
        size = newSize;
    }

    /**
     * Reallocates arrays if this is required by newSize param.
     * Extends arrays twice for expansion.
     * @param arr Object array
     * @param newSize New size of array
     * @return Reallocated array
     */
    private Object[] reallocateArrayIfNecessary(Object[] arr, int newSize) {
        if(newSize > allocatedSize) {// expansion is needed
            int tempSize = allocatedSize;
            while(tempSize < newSize) {
                tempSize *= 2;
            }
            allocatedSize = tempSize;
            return reallocateArray(arr, tempSize);
            // decrease is desirable, but greater than 32
        } else if(newSize < allocatedSize/2 && newSize >= 32) {
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
    private boolean isValidKey(Object o) {
        if(!(o instanceof String)) {
//            throw new ClassCastException("Attempting to use invalid key type");
            return false;
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
        //check type?
        for(int i = 0; i<size(); i++) {
            if(keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // check type?
        for(int i = 0; i<size(); i++) {
            if(vals[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        isValidKey(key);
        for(int i = 0; i<size(); i++) {
            if(keys[i].equals(key)) {
                return (V)vals[i];
            }
        }
        return null;
    }

    // returns the previous value associated with key,
    // or null if there was no mapping for key
    @Override
    public V put(K key, V value) {
        if(!(key instanceof String) || !(value instanceof Vector)) {
            System.err.println("Unsupported type");
            throw new ClassCastException("Wrong key or value type");
//            return null;
        }
        for(int i=0; i<size(); i++) {
            K current = (K)keys[i];
            if(current.equals(key)){
                V tmp = (V) vals[i];
                vals[i] = value;
                return tmp;
            }
        }
        System.out.println("adding new entry, key:" + key + ", value:" +value);
        setSize(size()+1);
        keys[size()-1] = key;
        vals[size()-1] = value;
        return null;
    }

    @Override
    public V remove(Object key) {
        if(!this.containsKey(key)) { // if map doesn't contain key
            return null;
        }
        int position = 0;
        for(int i = 0; i<size(); i++) {
            if(keys[i].equals(key)) {
                position = i;
                break;
            }
        }
        V tempV = (V)vals[position];
        keys[position] = keys[size()-1];
        vals[position] = vals[size()-1];
        this.setSize(size()-1);
        return tempV;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K,? extends V> entry : map.entrySet()) {
            this.put(entry.getKey(),entry.getValue());
        }
    }

    @Override
    public void clear() {
        int allocatedSize = 32;
        keys = new Object[allocatedSize];
        vals = new Object[allocatedSize];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> hSet = new HashSet<K>();
        for(int i = 0; i<size(); i++) {
            hSet.add((K)keys[i]);
        }
        return hSet;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> al = new ArrayList<V>();
        for(int i =0; i<size(); i++) {
            al.add((V)vals[i]);
        }
        return al;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}