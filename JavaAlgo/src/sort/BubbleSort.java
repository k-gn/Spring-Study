package sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {

        int[] arr = {5, 2, 3, 1};
//        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        boolean check = false;

        for(int i=1; i<n; i++) {
            for(int j=0; j<n-i; j++) {
                int temp = 0;
                if(arr[j] > arr[j+1]) {
                    check = true;
                    temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }

            if(!check) {
                System.out.println("이미 정렬이 된 배열이네요!");
                break;
            }
        }

        System.out.println(Arrays.toString(arr));

    }
}
