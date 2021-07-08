package algo.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class algo1 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = {1,2,3,4,5,-1};
        int key = Integer.parseInt(bf.readLine());
        arr[arr.length-1] = key; // 보초법
        int i = 0;
        int check = 0;
        while(true) {
//            if(i == arr.length) {
//                System.out.println("찾는 수가 없습니다.");
//                break;
//            }

            if(arr[i] == key) {
                break;
            }
            i++;
        }

        if(i == arr.length - 1) {
            check = -1;
        }

        if(check == -1) {
            System.out.println("찾는 수가 없습니다.");
        }else {
            System.out.println("찾는 수가 " + (i+1) + "번째에 있습니다.");
        }
    }
}
