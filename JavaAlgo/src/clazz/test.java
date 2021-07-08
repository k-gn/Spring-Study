package clazz;

import java.util.Arrays;
import java.util.Collections;

public class test {

    public static void main(String[] args) {

        Integer[] arr = {3, 4, 1, 2, 5};

        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(Arrays.toString(arr));
    }
}
