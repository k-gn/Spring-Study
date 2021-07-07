package algo;

import java.io.IOException;
import java.util.Arrays;

public class algo12 {

    public static void main(String[] args) throws IOException {

        // 진수 변환

        int i = 127;
        int num = 8;
        char[] index = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] arr = new char[32];
        int digit = 0;
        while(i > 0) {
            int temp = i % num;
            i = i / num;
            arr[digit++] = index[temp];
        }

        for(int j=0; j<digit; j++) {
            char temp = arr[j];
            arr[j] = arr[arr.length - 1 - j];
            arr[arr.length - 1 - j] = temp;
            System.out.println(arr);
        }

        for(char c : arr) {
            if(c != '\u0000') {
                System.out.print(c);
            }
        }

//        String str = Integer.toString(i, 16);
//        System.out.println(str);
    }

}
