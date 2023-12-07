package util;

public interface MySet<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(E e);
    boolean remove(Object o);
    void clear();

    Object[] toArray();

    // equals, hashcode, All 붙은 함수들

}
