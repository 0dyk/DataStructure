package util;

public interface MyList<E> {

    // 요소를 추가
    boolean add(E e);
    // 요소를 특정 위치에 추가
    void add(int index, E e);

    // 특정 위치의 요소를 삭제
    E remove(int index);
    // 해당 요소를 삭제
    boolean remove(Object o);

    // 특정 위치의 요소 반환
    E get(int index);
    // 특정 위치에 있는 요소를 교체
    void set(int index, E e);

    // 요소의 위치 반환
    int indexOf(Object o);
    // 요소의 위치 반환(역순)
    int lastIndexOf(Object o);

    // 요소가 리스트에 포함되어있는지 확인
    boolean contains(Object o);

    // 요소의 개수 반환
    int size();
    // 요소가 비어있는지 확인
    boolean isEmpty();
    // 요소를 모두 삭제
    void clear();

    // 배열로 변환
    Object[] toArray();

    // All 키워드 붙은 메소드
    // sort
    // equal
    // hash
    // sublist
    //  iterator
}
