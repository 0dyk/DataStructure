package util;

public class MyLinkedHashSet<E> extends MyHashSet<E> implements MySet<E> {

    // 어...?
    // map 생성 시 map의 DEFAULT 값을 쓰느냐
    // 여기 DEFAULT 값을 쓰느냐의 차이인가?

    public MyLinkedHashSet() {
        super(16, .75f, true);
    }
    public MyLinkedHashSet(int initialCapacity) {
        super(initialCapacity, .75f, true);
    }
    public MyLinkedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
    }

    // Iterator
}
