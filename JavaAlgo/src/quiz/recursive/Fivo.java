package quiz.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fivo {

    public static int fivo(int n) {

        if(n == 0) {
            return 0;
        }else if(n == 1) {
            return 1;
        }else {
            return fivo(n - 1) + fivo(n - 2);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

//        System.out.println(fivo(n));

        int a = 0;
        int b = 1;

        // 재귀가 아닌 반복문으로 하면 시간복잡도 면에서 더 효과적
        while(n > 0) {
            int temp = a;
            a = b;
            b = temp + a;
            n--;
        }
        System.out.println(a);
    }
}
