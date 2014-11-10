package vector.vectorContainer;

import java.lang.reflect.Array;
import java.util.*;

import vector.Vector;

/**
 * Created by Jack on 11/10/14.
 */
public class VectorMap<K extends String ,V extends Vector> implements Map<K,V> {
    Set<K> keys;
    List<V> values;
    int size;

    // 2 constructors
    public VectorMap() {
        keys = new LinkedHashSet<K>();
        values = new LinkedList<V>();
        size = 0;
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
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public V get(Object key) {
        int index = 0;
        for(K k : keys){
            if(k.equals(key)) {
                break;
                index++;
            }
            values.get(index);
        }

    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
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
