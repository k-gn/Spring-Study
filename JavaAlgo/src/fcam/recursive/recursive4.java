package fcam.recursive;

public class recursive4 {

    public static int recursive(int num) {

        System.out.println(num);
        if(num == 1) {
            return num;
        }else if(num % 2 == 0) {
            return recursive(num / 2);
        }else {
            return recursive(3 * num + 1);
        }

    }
    public static void main(String[] args) {

        int num = 3;
        System.out.println(recursive(num));
    }
}
