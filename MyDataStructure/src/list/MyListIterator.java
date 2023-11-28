package list;

public interface MyListIterator<E> {
    E next();
    E previos();

    boolean hasNext();
    boolean hasPrevios();

    void add(E e);
    void remove();

}
