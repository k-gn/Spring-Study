package algo.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class algo2 {

    public static void main(String[] args) {


        int[] arr = {1, 3, 3, 3, 3, 3, 13, 15, 17, 19};
        List<Integer> integers = new ArrayList<>();

        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(2);
        integers.add(3);
        integers.add(4);

        int key = 16;
        int left = 0;
        int right = arr.length - 1;
        int center = (left + right) / 2;
        boolean check = false;

        while(left <= right) {

            System.out.println("center : " + center);
            System.out.println("right : " + right);
            System.out.println("left : " + left);

            if(arr[center] == key) {
                check = true;
                break;
            }

            if(arr[center] < key) {
                left = center + 1;
                center = (left + right) / 2;
            }

            if(arr[center] > key) {
                right = center - 1;
                center = (left + right) / 2;
            }
            System.out.println("==============================");
            System.out.println("center : " + center);
            System.out.println("right : " + right);
            System.out.println("left : " + left);
            System.out.println("==============================");
        }

        if(check) {
            System.out.println("해당 값을 찾았습니다.");
        }else {
            System.out.println("해당 값이 존재하지 않습니다.");
        }

        int idx1 = Arrays.binarySearch(arr, 0, arr.length, 3);
//        System.out.println("idx : " + idx1);


    }
}
