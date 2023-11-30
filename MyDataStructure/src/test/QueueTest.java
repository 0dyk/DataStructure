package test;

import util.MyArrayDeque;
import util.MyLinkedList;
import util.MyQueue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

    /* ArrayDeque, LinkedList 차이점
    *  AD
    *  배열 기반(Head, Tail)
    *  2의 제곱수로 용량이 동적으로 커짐
    *
    *  LL
    *  Node 기반(First, Last)
    *  매번 새로운 노드 할당
    *  제거시 GC가 지워줌(아마도)
    *
    * */

    public static void main(String[] args) {

//        Queue<Integer> dq = new ArrayDeque<>();
//        Queue<Integer> lq = new LinkedList<>();

//        ArrayDequeQueueTest();
//        LinkedListQueueTest();


    }
    private static void ArrayDequeQueueTest() {
        MyQueue<Integer> q = new MyArrayDeque<>();

        q.offer(1);
        q.offer(2);
        q.offer(3);

        System.out.println(q.poll());
        System.out.println(q.peek());
        System.out.println(q.poll());
        System.out.println(q.poll());

        // null
        System.out.println(q.poll());
        // Error
//        System.out.println(q.remove());
    }

    private static void LinkedListQueueTest() {
        MyQueue<Integer> q = new MyLinkedList<>();

        q.offer(1);
        q.offer(2);
        q.offer(3);

        System.out.println(q.poll());
        System.out.println(q.peek());
        System.out.println(q.poll());
        System.out.println(q.poll());

        // null
        System.out.println(q.poll());
        // Error
//        System.out.println(q.remove());
    }
}
