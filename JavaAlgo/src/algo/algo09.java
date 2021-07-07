package algo;

import java.util.ArrayList;
import java.util.List;

public class algo09 {

    public static void main(String[] args) {

        int[] a = new int[5];
        a[1] = 37;
        a[2] = 51;
        for(int num : a) {
            System.out.println(num);
        }
        System.out.println("=====================================");
        int[] b = {1,2,3,4,5};
        int[] c = b.clone();
        for(int num : b) {
            System.out.println(num);
        }
        for(int num : c) {
            System.out.println(num);
        }
        System.out.println("=====================================");
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(22);
        arr.add(333);
        for(Integer num : arr) {
            System.out.println(num);
        }
    }

}
