package quiz.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 계수 정렬 (기존 정렬 시간복잡도 보다 성능이 좋다 -> O(N * N) => O(N))
public class array07 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수의 범위 (0 ~ 10000) 사실상 0은 제외
        int[] cnt = new int[10001];

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            // 해당 인덱스의 값을 1 증가
            cnt[Integer.parseInt(br.readLine())]++;
        }

        for(int i=0; i<10001; i++) {
            while(cnt[i] > 0){
                System.out.println(i);
                cnt[i]--;
            }
        }
//        int[] arr = new int[10001];
//
//        for(int i=0; i<n; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }
//
//        Arrays.sort(arr);
//
//        for(int i=0; i<n; i++) {
//            System.out.println(arr[i]);
//        }
    }

}
