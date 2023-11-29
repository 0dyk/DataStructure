package util;

public interface MyDeque<E> extends MyQueue<E>{
    void addFirst(E e);
    void addLast(E e);
    boolean offerFirst(E e);
    boolean offerLast(E e);

    E removeFirst();
    E removeLast();
    E pollFirst();
    E pollLast();

    E getFirst();
    E getLast();
    E peekFirst();
    E peekLast();

    // 검색 후 삭제(처음 찾는 값)
    boolean removeFirstOccurrence(Object o);
    boolean removeLastOccurrence(Object o);

    // Queue Method
    boolean add(E e);
    boolean offer(E e);
    E remove();
    E poll();
    E element();
    E peek();

    // stack method
    void push(E e);
    E pop();

    boolean remove(Object o);
    boolean contains(Object o);
    public int size();

    // iterator
}
