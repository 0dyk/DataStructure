package test;

import util.MyPriorityQueue;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest {

    public static void main(String[] args) {

//        Queue<Integer> pq = new PriorityQueue<>();
        PriorityQueueTest();
    }

    private static void PriorityQueueTest(){
        MyPriorityQueue<Integer> pq = new MyPriorityQueue<>();

        pq.offer(1);
        pq.offer(7);
        pq.offer(3);
        pq.offer(5);

        System.out.println(pq.peek());

        pq.poll();
        pq.offer(2);

        System.out.println("남아있는 큐");
        while(pq.size() != 0){
            System.out.println(pq.poll());
        }

    }
}
