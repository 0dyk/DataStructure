package util;

import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

// Map에서 공부하자
public class MyTreeSet<E> implements MyNavigableSet<E> {

    // 이것도 map을 쓰네
    private transient NavigableMap<E,Object> m;
    // 더미
    private static final Object PRESENT = new Object();

    MyTreeSet(NavigableMap<E,Object> m) {
        this.m = m;
    }
    public MyTreeSet() {
        this(new TreeMap<E,Object>());
    }

    public MyTreeSet(Comparator<? super E> comparator) {
        this(new TreeMap<>(comparator));
    }

    // Collection이나 SortedSet으로도 생성 가능

    @Override
    public int size() {
        return m.size();
    }

    @Override
    public boolean isEmpty() {
        return m.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return m.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return m.put(e, PRESENT)==null;
    }

    @Override
    public boolean remove(Object o) {
        return m.remove(o)==PRESENT;
    }

    @Override
    public void clear() {
        m.clear();
    }

    @Override
    public E first() {
        return m.firstKey();
    }
    @Override
    public E last() {
        return m.lastKey();
    }


    @Override
    public E lower(E e) {
        return m.lowerKey(e);
    }

    @Override
    public E floor(E e) {
        return m.floorKey(e);
    }

    @Override
    public E ceiling(E e) {
        return m.ceilingKey(e);
    }

    @Override
    public E higher(E e) {
        return m.higherKey(e);
    }

    @Override
    public E pollFirst() {
        Map.Entry<E,?> e = m.pollFirstEntry();
        return (e == null) ? null : e.getKey();
    }

    @Override
    public E pollLast() {
        Map.Entry<E,?> e = m.pollLastEntry();
        return (e == null) ? null : e.getKey();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }
}
