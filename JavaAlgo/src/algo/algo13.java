package algo;

import java.io.IOException;
import java.util.Arrays;

public class algo13 {

    public static void main(String[] args) throws IOException {

//        int n = 2;
//        boolean check = true;
//        for(int i=2; i<n; i++) {
//            if(n % i == 0) {
//                check = false;
//            }
//        }

//        if(check) {
//            System.out.println(n + " 은 소수입니다.");
//        }else {
//            System.out.println(n + " 은 소수가 아닙니다.");
//        }

        // 소수 구하기

        int idx = 0;
        int[] prime = new int[500];
        // 2, 3 은 당연한 소수
        prime[idx++] = 2;
        prime[idx++] = 3;

        for(int i=5; i<=1000; i += 2) {
            boolean check = false;
            // 해당 수의 제곱근보다 작거나 같은 소수에 의해 나누어 떨어지지 않으면 소수
            for(int j=1; prime[j]<=Math.sqrt(i); j++) {
                if(i % prime[j] == 0) {
                    check = true;
                    break;
                }
            }
            if(!check) {
//                System.out.println("소수 : " + i);
                prime[idx++] = i;
            }
        }
        System.out.println(Arrays.toString(prime));
    }

}
