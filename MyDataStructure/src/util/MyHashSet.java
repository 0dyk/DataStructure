package util;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MyHashSet<E> implements MySet<E>{

    // set은 그냥 map으로 다 하네?? ㅎㄷ
    private transient HashMap<E,Object> map;

    // 더미값
    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }
    public MyHashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }
    public MyHashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    // LinkedHashSet
    MyHashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    @Override
    public void clear() {
        map.clear();
    }

    // 나중에 확인
    @Override
    public Object[] toArray() {
        return map.keySet().toArray(new Object[0]);
    }

    // clone, write, read, Iterator
}
