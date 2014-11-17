package vector.vectorContainer;

import java.util.*;

import vector.Vector;

/**
 * Created by Jack on 11/10/14.
 */
public class VectorMap<K extends String ,V extends Vector> implements Map<K,V> {
    Set<Map.Entry<K,V>> entries;

    public VectorMap(){
        entries = new HashSet<Map.Entry<K, V>>();
    }

    public VectorMap(Map<K,V> map) {
        this();
        for(Map.Entry<K,V> entry : map.entrySet()) {
            entries.add(entry);
        }
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public boolean isEmpty() {
        return entries.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        for(Map.Entry<K,V> entry : entries) {
            if(entry.getKey().equals((K)key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Map.Entry<K,V> entry : entries) {
            if(entry.getValue().equals((V)value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for(Map.Entry<K,V> entry : entries) {
            if(entry.getKey().equals((K)key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        for(Map.Entry<K,V> entry : entries){
            if(entry.getKey().equals((K)key)) {
                V tmp = entry.getValue();
                entry.setValue(value);
                return tmp;
            }
        }
        CustomEntry<K,V> e = new CustomEntry<K, V>(key,value);
        entries.add(e);
        return null;
    }

    @Override
    public V remove(Object key) {
        for(Map.Entry<K,V> entry : entries) {
            if(entry.getKey().equals((K)key)) {
                V tmp = (V)entry.getValue();
                entries.remove(entry);
                return tmp;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(Map.Entry<? extends K, ? extends V> newEntry : m.entrySet()) {
            this.put(newEntry.getKey(),newEntry.getValue());
        }

    }

    @Override
    public void clear() {
        this.entries = new HashSet<Map.Entry<K, V>>();

    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<K>();
        for(Map.Entry<K,V> entry : entries) {
            s.add(entry.getKey());
        }
        return s;
    }

    @Override
    public Collection<V> values() {
        Collection<V> c = new ArrayList<V>();
        for(Map.Entry<K,V> entry : entries) {
            c.add(entry.getValue());
        }
        return c;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entries;
    }

    @Override
    public String toString() {
        String result = "VectorMap: \n";
        for(Map.Entry<K,V> entry : entries) {
            result += "\t" + entry.toString() + "\n";
        }
        return result;
    }
}

class CustomEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public CustomEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
    @Override
    public String toString() {
        return "Key:" + this.getKey() + " -> Value:" + this.getValue();
    }
}