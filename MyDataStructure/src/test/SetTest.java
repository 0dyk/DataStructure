package test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest {

    public static void main(String[] args) {
        Set<Integer> hs = new HashSet<>();
        Set<Integer> ls = new LinkedHashSet<>();

        // HashSetTest();
        // LinkedHashSetTest();
         EnumSetTest();
    }

    private static void HashSetTest(){
        // MySet<Integer> s = new MyHashSet<>();
    }
    private static void LinkedHashSetTest(){
        // MySet<Integer> s = new MyLinkedHashSet<>();
    }

    private enum abc{
        a, b, c;
    }
    private static void EnumSetTest(){
        EnumSet<abc> es = EnumSet.of(abc.a);
        es.add(abc.b);
        es.remove(abc.a);
        System.out.println(es.contains(abc.a));
        System.out.println(es.contains(abc.b));
    }
}
