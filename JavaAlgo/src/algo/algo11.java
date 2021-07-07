package algo;

import java.io.IOException;
import java.util.Arrays;

public class algo11 {

    public static boolean equalArr(int[] a, int[] b) {
        if(a.length != b.length) {
            return false;
        }

        for(int i=0; i<a.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }

    public static void rcopy(int[] a, int[] b) {
        b = a.clone(); // 배열 복사
        for(int i=0; i<b.length/2; i++) {
            int temp = b[i];
            b[i] = b[b.length - 1 - i];
            b[b.length - 1 - i] = temp;
        }
        System.out.println(Arrays.toString(b));
    }

    // 배열이 같은지 여부 확인
    public static void main(String[] args) throws IOException {

//       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//       String s = bf.readLine();
//       System.out.println(s);
        int[] num1 = {1, 2, 3, 4, 5};
        int[] num2 = {1, 2, 3, 4, 6};
        int[] num3 = null;

        rcopy(num1, num3);

        boolean check = equalArr(num1, num2);
        System.out.println(check ? "두 배열은 같습니다" : "두 배열은 같지 않습니다.");
    }

}
