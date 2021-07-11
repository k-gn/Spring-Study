package sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {5, 2, 3, 1, 4, 6};
//        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        boolean check = false;


        for(int i=1; i<n; i++) {
            int key = arr[i];
            int j = 0;
            for(j=i-1; j>=0; j--) {
                if(arr[j] > key) {
                    arr[j + 1] = arr[j];
                }else {
                    break;
                }
            }
            arr[j + 1] = key;
        }

        System.out.println(Arrays.toString(arr));
    }
}
