package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class algo010 {

    public static void main(String[] args) throws IOException {

//       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//       String s = bf.readLine();
//       System.out.println(s);
       int[] num = {1,2,3,4,5};

       System.out.println(Arrays.toString(num));
       for(int i=0; i<num.length/2; i++) {
           System.out.println(num[i] + " 와 " + num[num.length - 1 - i] + " 의 자리를 바꿉니다.");
           int temp = num[i];
           num[i] = num[num.length - 1 - i];
           num[num.length - 1 - i] = temp;
           System.out.println(Arrays.toString(num));
       }

    }

}
