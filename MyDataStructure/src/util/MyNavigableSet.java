package util;

public interface MyNavigableSet<E> extends MySortedSet<E> {

    E lower(E e);
    E floor(E e);
    E ceiling(E e);
    E higher(E e);
    E pollFirst();
    E pollLast();

    // SubSet 종류가 많지만 생략.

    // Iterator
}
