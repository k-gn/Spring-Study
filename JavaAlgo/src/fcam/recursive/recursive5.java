package fcam.recursive;

public class recursive5 {

    public static int recursive(int n) {

        switch(n) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            default:
                return recursive(n - 1) + recursive(n - 2) + recursive(n - 3);
        }
    }
    public static void main(String[] args) {

        // 1, 2, 3 의 합으로 나타낼 수 있는 경우의 수 구하기
        // f(N) = f(N-1) + f(N-2) + f(N-3)
        int num = 4;
        System.out.println(recursive(num));
    }
}
