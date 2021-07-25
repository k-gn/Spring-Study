package quiz.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fivo {

    public static int fivo(int n) {

        if(n == 0) {
            return 0;
        }else if(n == 1) {
            return 1;
        }else {
            return fivo(n - 1) + fivo(n - 2);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(fivo(n));
    }
}
