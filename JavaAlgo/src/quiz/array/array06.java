package quiz.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 행복
public class array06 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        for(int i=0; i<n; i++) {
            arr[i][0] = Integer.parseInt(br.readLine());
            arr[i][1] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                int a = o1[0] + o1[1];
                int b = o2[0] + o2[1];
                return a - b;
            }
        });

        for(int i=0; i<n; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }

}
