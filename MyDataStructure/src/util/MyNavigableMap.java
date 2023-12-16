package util;

import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedMap;

// C++의 lowerCase, upperCase Method
// 여러 subMap Method
public interface MyNavigableMap<K, V> extends MySortedMap<K, V> {

    // Key보다 작은 key 중 최대 key 반환, 없으면 null
    MyMap.Entry<K,V> lowerEntry(K key);
    K lowerKey(K key);

    // Key보다 작거나 같은 key 중 최대 키, 없으면 null
    MyMap.Entry<K,V> floorEntry(K key);
    K floorKey(K key);

    // Key보다 크거나 같은 key 중 최소 키, 없으면 null
    MyMap.Entry<K,V> ceilingEntry(K key);
    K ceilingKey(K key);

    // Key보다 큰 key 중 최소 key 반환, 없으면 null
    MyMap.Entry<K,V> higherEntry(K key);
    K higherKey(K key);

    // Peek, Poll
    MyMap.Entry<K,V> firstEntry();
    MyMap.Entry<K,V> lastEntry();
    MyMap.Entry<K,V> pollFirstEntry();
    MyMap.Entry<K,V> pollLastEntry();

    // descending -> 역순의 map 반환
    MyNavigableMap<K,V> descendingMap();
    MyNavigableSet<K> navigableKeySet();
    MyNavigableSet<K> descendingKeySet();

    // fromKey ~ toKey
    MyNavigableMap<K,V> subMap(K fromKey, boolean fromInclusive,
                             K toKey,   boolean toInclusive);
    // headKey ~ toKey
    MyNavigableMap<K,V> headMap(K toKey, boolean inclusive);
    // fromKey ~ tailKey
    MyNavigableMap<K,V> tailMap(K fromKey, boolean inclusive);

    // [fromKey, toKey)
    MySortedMap<K,V> subMap(K fromKey, K toKey);
    // [headKey, toKey)
    MySortedMap<K,V> headMap(K toKey);
    // [fromKey, tailKey]
    MySortedMap<K,V> tailMap(K fromKey);
}
