package fcam.recursive;

public class Factorial {

    public static int factorial(int num) {

//        if(num == 1) {
//            return num;
//        }else {
//            return num + factorial(num - 1);
//        }

        if(num <= 1) {
            return num;
        }

        int result = num * factorial(num - 1);
        return result;
    }
    public static void main(String[] args) {

        System.out.println(factorial(5));
    }
}
