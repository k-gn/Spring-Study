package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic01_t {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[8];
        for(int i=0; i<8; i++) {
            System.out.println("1 ~ 8 까지의 숫자를 입력하세요.");
            arr[i] = Integer.parseInt(bf.readLine());
        }

        isSort(arr);
    }

    public static void isSort(int[] arr) {

        boolean isAscending = true;
        boolean isDescending = true;

        // 오름차순인지 내림차순인지만 확인하면 된다.
        for (int i=1; i<arr.length; i++) {
            if(arr[i] > arr[i - 1]) {
                isDescending = false;
            }else if(arr[i] < arr[i - 1]) {
                isAscending = false;
            }
        }

        if(isAscending) {
            System.out.println("ascending");
        }else if(isDescending) {
            System.out.println("descending");
        }else {
            System.out.println("mixed");
        }
    }
}
