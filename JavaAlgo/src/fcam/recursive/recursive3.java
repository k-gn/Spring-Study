package fcam.recursive;

import java.util.Arrays;

public class recursive3 {

    public static boolean recursive(String name) {

        // 회문 검증
        if(name.length() <= 1) {
            return true;
        }

        if(name.charAt(0) == name.charAt(name.length() - 1)) {
            return recursive(name.substring(1, name.length() - 1));
        }else {
            return false;
        }
    }
    public static void main(String[] args) {

        String name = "StetSb";
        // 1 2 3 4 5
        System.out.println(recursive(name));
    }
}
