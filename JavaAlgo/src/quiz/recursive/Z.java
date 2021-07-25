package quiz.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Z {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input, " ");
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(zVisit(N, r, c));
    }

    public static int zVisit(int N, int r, int c) {
        if (N == 0) {
            return 0;
        } else {
            int dist = 1 << (N - 1);
            // 좌표를 움직일 거리
            if (r < dist && c < dist) return zVisit(N - 1, r, c);
            else if (r < dist && c >= dist) return (dist * dist) + zVisit(N - 1, r, c - dist);
            else if (r >= dist && c < dist) return (dist * dist) * 2 + zVisit(N - 1, r - dist, c);
            else return (dist * dist) * 3 + zVisit(N - 1, r - dist, c - dist);
        }
    }

}
