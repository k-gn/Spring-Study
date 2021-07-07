package algo;

// 자리수 출력하기
public class algo06 {

    public static void main(String[] args) {

        int n = 123;
        int count = 0;
        int length = (int)(Math.log10(n)+1);
        System.out.println("자리수 : " + length);

        while(n != 0) {
            n = n / 10;
            System.out.println("n : " + n);
            count++;
        }
        System.out.println("자리수 : " + count);


    }

}
