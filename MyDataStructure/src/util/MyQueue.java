package util;

public interface MyQueue<E> {
    boolean add(E e);
    boolean offer(E e);
    E remove();
    E poll();
    E peek();
    E element();

    // add, remove, element : 오류 -> return false or null
    // offer, poll, peek : 오류 -> throw exception
}
