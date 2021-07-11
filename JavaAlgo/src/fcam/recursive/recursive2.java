package fcam.recursive;

import java.util.Arrays;

public class recursive2 {

    public static int recursive(int[] arr) {

        // 배열 요소의 총합 (재귀함수 사용)
        // 1 3 5 7 9

        if(arr.length == 1) {
            return arr[0];
        }else {
            return arr[0] + recursive(Arrays.copyOfRange(arr, 1, arr.length));
        }

    }
    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9};
        // 1 2 3 4 5
        System.out.println(recursive(arr));
    }
}
