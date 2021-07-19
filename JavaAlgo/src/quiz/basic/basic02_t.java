package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic02_t {

    // 최대 n값의 3장의 카드를 고르는 블랙잭
    // n(n-1)(n-2) / 3!
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" 카드의 개수 (3 ~ 100) ");
        int cardCount = Integer.parseInt(bf.readLine());

        System.out.println(" 카드의 값 (10 ~ 300000) ");
        int cardNum = Integer.parseInt(bf.readLine());

        int[] arr = new int[cardCount];

        for(int i=0; i<cardCount; i++) {
            System.out.println("100000을 넘지 않는 숫자를 입력하세요.");
            arr[i] = Integer.parseInt(bf.readLine());

        }

        blackJack(cardCount, cardNum, arr);
    }

    private static void blackJack(int cardCount, int cardNum, int[] arr) {

        int result = 0;

        for(int i=0; i<arr.length; i++) {

            for(int j=i+1; j<arr.length; j++) {

                for(int k=j+1; k<arr.length; k++) {

                    int sum = arr[i] + arr[j] + arr[k];
                    if(sum <= cardNum && sum > result) {
                        result = sum;
                    }
                }
            }

        }

        System.out.println(result);
    }

}
