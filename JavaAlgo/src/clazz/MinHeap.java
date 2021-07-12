package clazz;

import java.util.ArrayList;

public class MinHeap {

    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<Integer>();
        heap.add(0); // 0번째 인덱스는 사용 안합
    }
}
