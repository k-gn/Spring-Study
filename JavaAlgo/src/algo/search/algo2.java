package algo.search;

import java.util.Arrays;

public class algo2 {

    public static void main(String[] args) {


        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

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

        int idx = Arrays.binarySearch(arr, 17);
        System.out.println("idx : " + idx);

    }
}
