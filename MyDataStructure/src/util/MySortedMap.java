package util;

import java.util.*;

// Comparator 추가
// 우선순위에 따른 first, last Method 추가
public interface MySortedMap<K,V> extends MyMap<K,V> {

    Comparator<? super K> comparator();

    MySortedMap<K,V> subMap(K fromKey, K toKey);

    // [head, toKey)
    MySortedMap<K,V> headMap(K toKey);

    // [fromKey, tail]
    MySortedMap<K,V> tailMap(K fromKey);

    K firstKey();
    K lastKey();

    Set<K> keySet();
    Collection<V> values();
    Set<MyMap.Entry<K, V>> entrySet();
}
