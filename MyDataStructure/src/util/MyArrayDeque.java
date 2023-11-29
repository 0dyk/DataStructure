package util;


import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class MyArrayDeque<E> implements MyDeque<E> {

    // transient : 중첩 클래스에서 접근하는 것을 단순화하기 위해서라는데
    // inner class에서 private에 접근하는게 귀찮으니까..?

    transient Object[] elements;
    transient int head;
    transient int tail;

    private static final int MIN_INITIAL_CAPACITY = 8;

    // DEFAULT_CAPACITY = 16
    public MyArrayDeque(){
        elements = new Object[16];
    }

    // ArrayDequeue은 자신보다 큰 2의 제곱수로 설정함.(최소값 8)
    public MyArrayDeque(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;

        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)
                initialCapacity >>>= 1;
        }

        elements = new Object[initialCapacity];
    }

    // Collection이 들어오는 생성자도 있음. 생략

    // 용량이 꽉차면 2배로 늘린다.
    private void doubleCapacity(){
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big ㅋㅋ");
        Object[] a = new Object[newCapacity];

        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);

        elements = a;
        head = 0;
        tail = n;
    }

    // head는 1씩 작아진 후 대입.
    // 이래서 2의 제곱수로 했나보네 ㅎㄷ
    @Override
    public void addFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail)
            doubleCapacity();
    }

    // tail은 넣고 나서 1씩 커진다.
    // 이게 반대로 되는구나 ㅎㄷ
    @Override
    public void addLast(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[tail] = e;
        if ( (tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        E x = pollFirst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    @Override
    public E removeLast() {
        E x = pollLast();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    // 형변환 경고를 무시한다는 거겠지?
    @Override
    public E pollFirst() {
        int h = head;
        @SuppressWarnings("unchecked")
        E result = (E) elements[h];
        if (result == null)
            return null;
        elements[h] = null;
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    @Override
    public E pollLast() {
        int t = (tail - 1) & (elements.length - 1);
        @SuppressWarnings("unchecked")
        E result = (E) elements[t];
        if (result == null)
            return null;
        elements[t] = null;
        tail = t;
        return result;
    }

    @Override
    public E getFirst() {
        @SuppressWarnings("unchecked")
        E result = (E) elements[head];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    @Override
    public E getLast() {
        @SuppressWarnings("unchecked")
        E result = (E) elements[(tail - 1) & (elements.length - 1)];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    @Override
    public E peekFirst() {
        return (E) elements[head];
    }

    @Override
    public E peekLast() {
        return (E) elements[(tail - 1) & (elements.length - 1)];
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = head;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i + 1) & mask;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = (tail - 1) & mask;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i - 1) & mask;
        }
        return false;
    }

    // 특정 index 값을 지우고 head, tail 재조정 하는 함수.
    private boolean delete(int i) {
        //  checkInvariants();
        assert elements[tail] == null;
        assert head == tail ? elements[head] == null :
                (elements[head] != null &&
                        elements[(tail - 1) & (elements.length - 1)] != null);
        assert elements[(head - 1) & (elements.length - 1)] == null;
        // end checkInvariants

        final Object[] elements = this.elements;
        final int mask = elements.length - 1;
        final int h = head;
        final int t = tail;
        final int front = (i - h) & mask;
        final int back  = (t - i) & mask;

        // Invariant: head <= i < tail mod circularity
        if (front >= ((t - h) & mask))
            throw new ConcurrentModificationException();

        // Optimize for least element motion
        if (front < back) {
            if (h <= i) {
                System.arraycopy(elements, h, elements, h + 1, front);
            } else { // Wrap around
                System.arraycopy(elements, 0, elements, 1, i);
                elements[0] = elements[mask];
                System.arraycopy(elements, h, elements, h + 1, mask - h);
            }
            elements[h] = null;
            head = (h + 1) & mask;
            return false;
        } else {
            if (i < t) { // Copy the null tail as well
                System.arraycopy(elements, i + 1, elements, i, back);
                tail = t - 1;
            } else { // Wrap around
                System.arraycopy(elements, i + 1, elements, i, mask - i);
                elements[mask] = elements[0];
                System.arraycopy(elements, 1, elements, 0, t);
                tail = (t - 1) & mask;
            }
            return true;
        }
    }

    // *** Queue methods : 선입 선출 ***
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }
    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }
    @Override
    public E remove() {
        return removeFirst();
    }
    @Override
    public E poll() {
        return pollFirst();
    }
    @Override
    public E element() {
        return getFirst();
    }
    @Override
    public E peek() {
        return peekFirst();
    }


    // *** Stack methods : 선입 후출 ***
    @Override
    public void push(E e) {
        addFirst(e);
    }
    @Override
    public E pop() {
        return removeFirst();
    }


    @Override
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = head;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equals(x))
                return true;
            i = (i + 1) & mask;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    public void clear() {
        int h = head;
        int t = tail;
        if (h != t) { // clear all cells
            head = tail = 0;
            int i = h;
            int mask = elements.length - 1;
            do {
                elements[i] = null;
                i = (i + 1) & mask;
            } while (i != t);
        }
    }

    // public Object[] toArray()
    // public ArrayDeque<E> clone()
    // Iterator ...
}
