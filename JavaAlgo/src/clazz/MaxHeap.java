package clazz;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {

    private List<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<Integer>();
        heap.add(0); // 0번째 인덱스는 사용 안합
    }

    public static int getParentIdx(int childIdx) {
        return childIdx / 2;
    }

    public static int getChildLeftIdx(int parentIdx) {
        return parentIdx * 2;
    }

    public static int getChildRightIdx(int parentIdx) {
        return parentIdx * 2 + 1;
    }

}
