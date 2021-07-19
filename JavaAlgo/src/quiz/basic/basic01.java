package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic01 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[8];
        for(int i=0; i<8; i++) {
            System.out.println("1 ~ 8 까지의 숫자를 입력하세요.");
            arr[i] = Integer.parseInt(bf.readLine());
        }

        System.out.println(isSort(arr));
    }

    public static String isSort(int[] arr) {

        int check1 = 0;
        int check2 = 0;

        String result = "";

        for(int i=0; i<arr.length / 2; i++) {
            if(arr[i] + arr[arr.length - (i + 1)] == arr.length + 1 && arr[0] == 1) {
                if (check1 == i) {
                    result = "ascending";
                }
                check1++;
            }else if(arr[i] + arr[arr.length - (i + 1)] == arr.length + 1 && arr[0] == 8) {
                if (check2 == i) {
                    result = "descending";
                }
                check2++;
            }else {
                result = "mixed";
            }
        }

        return result;
    }
}
