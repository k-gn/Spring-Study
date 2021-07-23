package quiz.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class array02 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer> arr = new ArrayList<>();

        for(int i=0; i<n; i++) {
            arr.add(Integer.valueOf(br.readLine()));
        }

        Collections.sort(arr);

        System.out.println(arr);
    }
}
