package algo;

import java.util.Arrays;

// 삽입 정렬
public class algo03 {

    public static void insertionSort(int[] arr) {

        int i = 0, j = 0, key = 0;

        for(i = 1; i < arr.length; i++) {
            key = arr[i];
            for(j = i - 1; j >= 0; j--) {
                if(arr[j] > key) arr[j + 1] = arr[j];
                else break;
            }
            arr[j + 1] = key;
        }

    }

    public static void main(String[] args) {

        int[] arr = {80, 50, 70, 10, 60, 20, 40, 30};

        insertionSort(arr);
    }

}
