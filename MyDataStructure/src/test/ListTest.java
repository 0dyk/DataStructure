package test;

import util.MyArrayList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new LinkedList<>();

//        ArratListTest();
        LinkedListTest();

    }

    private static void ArratListTest() {
        MyArrayList<Integer> list = new MyArrayList<>(4);

        list.add(1);
        list.add(3);
        list.add(2);
        list.add(6);
        System.out.println(list);

        // resize -> 6
        list.add(4);
        System.out.println(list);

        System.out.println(list.indexOf(2));
        System.out.println(list.lastIndexOf(2));

        System.out.println(list.remove(0));
        System.out.println(list.remove(Integer.valueOf(2)));
        System.out.println(list);

        // default_capacity = 10이라서 10개 나옴
        list.clear();
        System.out.println(list);    
    }

    private static void LinkedListTest() {

    }
}
