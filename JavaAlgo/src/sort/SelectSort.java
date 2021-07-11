package sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {

        int[] arr = {5, 2, 3, 1, 4, 6};
//        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        boolean check = false;

        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                int temp = 0;
                if(arr[i] > arr[j]) {
                    check = true;
                    temp = arr[i];
                    arr[i] = arr[j];
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
