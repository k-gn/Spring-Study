package fcam;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class queue {

    private static Queue<String> arr = new LinkedList<>();

    public static void enQueue(String data) {
        arr.add(data);
    }

    public static void deQueue() {
        if(!arr.isEmpty()) {
            arr.poll();
        }
    }

    public static void main(String[] args) {

        enQueue("hello");
        enQueue("world");
        System.out.println(arr.size());
        deQueue();
        System.out.println(arr.size());
    }
}
