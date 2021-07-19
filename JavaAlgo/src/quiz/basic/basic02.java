package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic02 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[8];
        for(int i=0; i<8; i++) {
            System.out.println("1 ~ 8 까지의 숫자를 입력하세요.");
            arr[i] = Integer.parseInt(bf.readLine());
        }

    }

}
