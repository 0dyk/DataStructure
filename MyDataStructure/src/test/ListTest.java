package test;

import util.MyArrayList;
import util.MyLinkedList;
import util.MyList;

import java.util.*;

public class ListTest {

    /* ArrayList, LinkedList 차이점
    *
    *  Queue 차이점과 동일
    *
    * */
    public static void main(String[] args) {

//        List<Integer> list = new ArrayList<>();
//        List<Integer> list2 = new LinkedList<>();
//          Stack<Integer> s = new Stack<>();

//        ArratListTest();
//        LinkedListTest();

    }

    private static void ArratListTest() {
        MyList<Integer> list = new MyArrayList<>(4);

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
        MyList<Integer> list = new MyLinkedList<>();

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
}
