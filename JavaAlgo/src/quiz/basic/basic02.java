package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class basic02 {

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

        for(int i=0; i<cardCount-2; i++) {

            for(int j=1; j<cardCount - 1; j++) {
                int max = arr[i] + arr[j] + arr[j+1];
                if(max <= cardNum && max > result) {
                    result = max;
                }
            }

        }

        System.out.println(result);
    }

}
