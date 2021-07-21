package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class basic04 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("t : ");
        int t = sc.nextInt();
        int n, m, count;
        // m의 인덱스는 0부터
        for (int i = 0; i < t; i++) {
            LinkedList<int[]> queue = new LinkedList<>();
            count = 0;
            System.out.println("n : ");
            n = sc.nextInt();
            System.out.println("m : ");
            m = sc.nextInt();
            for (int j = 0; j < n; j++) {
                // 인덱스, 중요도 입력받기
                System.out.println("중요도 : ");
                queue.add(new int[]{j, sc.nextInt()});
            }

            while (!queue.isEmpty()) {
                // 큐가 빌 때까지
                int[] now = queue.poll();
                boolean able = true;

                System.out.println("now : " + Arrays.toString(now));
                for (int[] q : queue)
                    if (q[1] > now[1]) able = false;

                if (able) {
                    count++;
                    System.out.println("count : " + count);
                    if (now[0] == m) break;
                } else queue.add(now);
            }

            System.out.println(count);
        }
    }

}
