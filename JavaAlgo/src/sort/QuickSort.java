package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuickSort {

    public List<Integer> qsort(List<Integer> data) {

        System.out.println("data : " + data);
        System.out.println("==========================");

        if(data.size() <= 1) {
            System.out.println("end recursive");
            return data;
        }

        List<Integer> left = new ArrayList<>(); // pivot 보다 작은 값을 저장할 배열
        List<Integer> right = new ArrayList<>(); // pivot 보다 큰 값을 저장할 배열
        List<Integer> equal = new ArrayList<>(); // pivot 을 저장할 배열

        int pivot = data.get(data.size()/2);
        System.out.println("pivot : " + pivot);
        System.out.println("==========================");

        for(Integer value : data) {
            if(pivot > value) {
                left.add(value);
            }else if(pivot < value){
                right.add(value);
            }else {
                equal.add(value);
            }
        }


        System.out.println("left : " + Arrays.toString(left.toArray()));
        System.out.println("right : " + Arrays.toString(right.toArray()));
        System.out.println("equal : " + Arrays.toString(equal.toArray()));
        System.out.println("==========================");
        
        // 배열 3개를 스트림으로 묶은 후 하나의 배열로 합쳐서 리턴
        return Stream.of(qsort(left), equal, qsort(right))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {

        QuickSort quickSort = new QuickSort();

        List<Integer> datas = new ArrayList<>();
        datas.add(20);
        datas.add(15);
        datas.add(5);
        datas.add(10);
        datas.add(25);
        datas.add(70);
        datas.add(50);

        List<Integer> result = quickSort.qsort(datas);
        result.stream().forEach(n -> System.out.print(n + " "));
    }
}
