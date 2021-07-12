package clazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxHeap {

    private List<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<Integer>();
        heap.add(0); // 0번째 인덱스는 사용 안합
    }

    public void insert(int val) {
        //맨 마지막 위치에 삽입
        heap.add(val);

        int  p = heap.size()-1; //새로 넣은 노드의 인덱스 위치 정보
        //루트까지 자식이 더 크면 교환
        while(p>1 && heap.get(p) > heap.get(p/2)) {
            int tmp = heap.get(p/2);
            heap.set(p/2, heap.get(p));
            heap.set(p, tmp);

            p /= 2;
        }
    }

    public int delete() {
        if (heap.size() <= 1)
            return 0;
        int deleteItem = heap.get(1); // 삭제할 노드 = 루트노드

        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int pos = 1;
        while((pos*2) < heap.size()) { // 해당 인덱스의 왼쪽 자식이 있는지 여부를 판단

            int max = heap.get(pos*2);
            int maxPos = pos*2;

            //오른쪽 자식이 존재하고 오른쪽 자식이 왼쪽 자식보다 클때 바꿀 자식 오른쪽으로 설정
            if((pos*2+1)<heap.size() && max < heap.get(pos*2+1)) {
                max = heap.get(pos*2+1);
                maxPos = pos*2+1;
            }

            //부모가 더 크면 끝
            if(heap.get(pos) > max){
                break;
            }

            //자식이 더 크면 교환
            int tmp = heap.get(pos);
            heap.set(pos, max);
            heap.set(maxPos, tmp);
            pos = maxPos;
        }

        return deleteItem;
    }

    public void printHeap() {
        heap.stream().forEach(n -> System.out.println(n + " "));
    }
}
