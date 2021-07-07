package algo;

// 1 ~ n 까지의 합
public class algo05 {

    public static void main(String[] args) {

        int n = 10;
//        int sum = 0;
//        for(int i=1; i<=n; i++) {
//            sum += i;
//        }
        int sum = (n * (n + 1)) / 2; // 가우스 덧셈 n(n+1)/2
        System.out.println("1 ~ n 까지의 합 : " + sum);
    }

}
