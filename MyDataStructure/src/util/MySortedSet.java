package util;

import java.util.Comparator;
import java.util.SortedSet;

// Comparator 및 SubSet
public interface MySortedSet<E> extends MySet<E> {

    // Comparator 반환
    Comparator<? super E> comparator();

    E first();
    E last();

    // SubSet
//    MySortedSet<E> subSet(E fromElement, E toElement);
//    MySortedSet<E> headSet(E toElement);
//    MySortedSet<E> tailSet(E fromElement);

    // Iterator
}
