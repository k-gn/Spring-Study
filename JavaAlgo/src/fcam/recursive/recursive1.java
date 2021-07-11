package fcam.recursive;

public class recursive1 {

    public static int factorial(int num) {

        if(num == 1) {
            return num;
        }else {
            return num * factorial(num - 1);
        }

    }
    public static void main(String[] args) {

        // 1 2 3 4 5
        System.out.println(factorial(10));
    }
}
