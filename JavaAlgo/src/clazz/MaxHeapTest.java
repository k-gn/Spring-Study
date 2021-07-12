package clazz;

import java.util.ArrayList;
import java.util.List;

public class MaxHeapTest {

    public static void main(String[] args) {

        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(10);
        maxHeap.insert(15);
        maxHeap.insert(20);
        maxHeap.insert(5);
        maxHeap.insert(30);
        maxHeap.printHeap();
        System.out.println("================================");
        maxHeap.delete();
        maxHeap.printHeap();
        System.out.println("================================");
        maxHeap.delete();
        maxHeap.printHeap();
        System.out.println("================================");
        maxHeap.delete();
        maxHeap.printHeap();
    }


}
