package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class basic07 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> nArr = new ArrayList<>(n);
        StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");

        while (tokenizer.hasMoreTokens()) {
            nArr.add(Integer.parseInt(tokenizer.nextToken()));
        }

//        for(int i=0; i<n; i++) {
//            int data = Integer.parseInt(br.readLine());
//            nArr.add(data);
//        }

        int m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++) {
            int data = Integer.parseInt(br.readLine());

            if(nArr.contains(data)) {
                System.out.println("1");
            }else {
                System.out.println("0");
            }
        }
    }
}
