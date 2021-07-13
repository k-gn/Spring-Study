package sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public List<Integer> spilt(List<Integer> data) {

        if(data.size() <= 1) {
            return data;
        }

        int midIdx = data.size() / 2;
        List<Integer> left = spilt(data.subList(0, midIdx));
        List<Integer> right = spilt(data.subList(midIdx, data.size()));

        System.out.println("left : " + left);
        System.out.println("right : " + right);

        return merge(left, right);
    }

    public List<Integer> merge(List<Integer> left, List<Integer> right) {

        System.out.println("merge ========================");
        List<Integer> result = new ArrayList<>();

        int leftIdx =0, rightIdx = 0;

        // case 1. left/right 가 아직 남아있을 때
        while (left.size() > leftIdx && right.size() > rightIdx) {

            if(left.get(leftIdx) > right.get(rightIdx)) {
                result.add(right.get(rightIdx));
                rightIdx++;
            }else {
                result.add(left.get(leftIdx));
                leftIdx++;
            }
        }

        // case 2. left 만 남아있을 때
        while (left.size() > leftIdx) {
            result.add(left.get(leftIdx));
            leftIdx++;
        }

        // case 3. right 만 남아있을 때
        while (right.size() > rightIdx) {
            result.add(right.get(rightIdx));
            rightIdx++;
        }

        return result;
    }

    public static void main(String[] args) {

        List<Integer> datas = new ArrayList<>();
        datas.add(20);
        datas.add(15);
        datas.add(5);
        datas.add(10);
        datas.add(25);
        datas.add(70);
        datas.add(50);
        datas.add(60);

        MergeSort mergeSort = new MergeSort();
        List<Integer> merged = mergeSort.spilt(datas);
        System.out.println(merged);
    }
}
