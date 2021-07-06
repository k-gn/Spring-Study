package algo;

// 자리수 출력하기
public class algo06 {

    public static void main(String[] args) {

        int n = 123;
        int count = 0;

        while(n != 0) {
            n = n / 10;
            System.out.println("n : " + n);
            count++;
        }
        System.out.println("자리수 : " + count);

    }

}
