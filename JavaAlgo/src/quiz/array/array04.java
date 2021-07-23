package quiz.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 백준 행복
public class array04 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int min = 1000;
        int max = 0;
        int[] arr = new int[n];

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        int count = stringTokenizer.countTokens();
        for(int i=0; i<count; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int i=0; i<n; i++) {
            if(max < arr[i]) max = arr[i];
            if(min > arr[i]) min = arr[i];
        }
        System.out.println(max - min);

//        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
//        List<Integer> arr = new ArrayList<>(n);

//        while (stringTokenizer.hasMoreTokens()) {
//            arr.add(Integer.valueOf(stringTokenizer.nextToken()));
//        }

//        int num1 = arr.stream().max(Integer::compareTo).get();
//        int num2 = arr.stream().min(Integer::compareTo).get();

//        System.out.println(num1 - num2);
    }
}
