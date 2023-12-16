package util;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;

public interface MyMap<K, V> {

    int size();
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    V put(K key, V value);
    V remove(Object key);
    void clear();


    // 이런 것도 있네 ㅎㄷ
    Set<K> keySet();
    Collection<V> values();
    Set<Map.Entry<K, V>> entrySet();


    // 키에 해당하는 값이 없으면 defaultValue
    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
                ? v
                : defaultValue;
    }

    // key에 해당하는 값이 없는 경우만 풋
    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }

        return v;
    }

    // (key, value)가 존재하는 경우 삭제
    default boolean remove(Object key, Object value) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, value) ||
                (curValue == null && !containsKey(key))) {
            return false;
        }
        remove(key);
        return true;
    }

    // (key, oldvalue)가 존재하는 경우 newVlaue 변경
    default boolean replace(K key, V oldValue, V newValue) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, oldValue) ||
                (curValue == null && !containsKey(key))) {
            return false;
        }
        put(key, newValue);
        return true;
    }

    // key에 해당하는 값이 존재하는 경우
    default V replace(K key, V value) {
        V curValue;
        if (((curValue = get(key)) != null) || containsKey(key)) {
            curValue = put(key, value);
        }
        return curValue;
    }

    //    default void forEach(BiConsumer<? super K, ? super V> action) {
//        Objects.requireNonNull(action);
//        for (Map.Entry<K, V> entry : entrySet()) {
//            K k;
//            V v;
//            try {
//                k = entry.getKey();
//                v = entry.getValue();
//            } catch(IllegalStateException ise) {
//                // this usually means the entry is no longer in the map.
//                throw new ConcurrentModificationException(ise);
//            }
//            action.accept(k, v);
//        }
//    }

    // ㅇ것 말고도 많네요...


    interface Entry<K,V> {
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object o);
        int hashCode();
        public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K,V>> comparingByKey() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }
        public static <K, V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue() {
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        }
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
        }
        public static <K, V> Comparator<Map.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<Map.Entry<K, V>> & Serializable)
                    (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
        }
    }
}
