package util;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {

    // 기본 용량
    private static final int DEFAULT_CAPACITY = 10;

    // 요소의 개수
    private int size;

    Object[] elementData;

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    public MyArrayList(int size) {
        this.size = 0;

        if(size > 0){
            this.elementData = new Object[size];
        }
        else if(size == 0){
            this.elementData = new Object[DEFAULT_CAPACITY];
        }
        else if(size < 0){
            throw new RuntimeException(new IllegalAccessException("리스트의 크기는 0보다 작을 수 없습니다."));
        }
    }

    // 보기 편하도록 resize()를 제외하고는
    // 동일한 기능이더라도 따로 빼지 않았음. 범위체크 등
    private void resize(int minCapacity){
        int elementCapacity = elementData.length;

        // 기존 배열의 1.5배의 크기로 새로운 배열을 만든 후
        // 기존의 값들을 복사.
        if(elementCapacity == size) {
            int newCapacity = elementCapacity + (elementCapacity >> 1);

            // 크기가 안 늘어난 경우가 있으면
            if(newCapacity - minCapacity < 0){
                elementData = Arrays.copyOf(elementData, minCapacity);
            }else{
                elementData = Arrays.copyOf(elementData, newCapacity);
            }
        }
    }

    @Override
    public boolean add(E e) {
        resize(size + 1);

        elementData[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E e) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        // 배열의 끝에 삽입하는 경우
        if(index == size){
            add(e);
        }else{
            resize(size + 1);

            // index를 기준으로 뒤의 값들을 밀어주기
            System.arraycopy(elementData, index, elementData, index + 1,
                    size - index);
            elementData[index] = e;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        E oldValue = (E) elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null;

        return oldValue;
    }
    @Override
    public boolean remove(Object o) {
        int idx = indexOf(o);

        if(idx == -1) return false;

        remove(idx);
        return true;
    }

    @Override
    public E get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        return (E)elementData[index];
    }

    @Override
    public void set(int index, E e) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        elementData[index] = e;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0 ? true : false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }

    // All 붙은 메소드
    // clone()
    // forEach
    // removeIf : js의 filter 같은거
    // ex) list.removeIf(n -> n % 2 == 0);
    // 짝수 제거

    class ListIterator implements MyListIterator<E> {
        private int nextIndex = 0;

        @Override
        public E next() {
            return (E) elementData[nextIndex++];
        }

        @Override
        public E previos() {
            return (E) elementData[--nextIndex];
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }
        @Override
        public boolean hasPrevios() {
            return nextIndex > 0;
        }

        @Override
        public void add(E e) {
            MyArrayList.this.add(nextIndex, e);
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(nextIndex -1);
        }
    }
    public ListIterator listIterator() {
        return new ListIterator();
    }


}