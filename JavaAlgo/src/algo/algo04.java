package algo;

import java.util.Scanner;

// 중앙값 구하기
public class algo04 {

    public static int mid(int a, int b, int c) {
        // 1 2 3
        if((a < b && a > c) || (a > b && a < c)) {
            return a;
        }else if((b < a && b > c) || (b > a && b < c)) {
            return b;
        }else {
            return c;
        }
    }

    public static void main(String[] args) {

        int a = 1, b = 4, c = 8;
        int result = mid(a, b, c);
        System.out.println("mid : " + result);
    }

}
