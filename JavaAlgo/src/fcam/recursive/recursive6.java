package fcam.recursive;

public class recursive6 {

    // 피보나치 수열
    public static int recursive(int n) {
        if(n <= 1) {
            return n;
        }else {
            return recursive(n - 2) + recursive(n - 1);
        }
    }

    // 동적 계획법
    // 계산된 값이 저장되고 다시 필요할 때 저장된 값을 재활용 가능
    public static int cacheFibo(int num) {
        int[] cache = new int[num + 1];

        cache[0] = 0;
        cache[1] = 1;

        for(int i=2; i<cache.length; i++) {
            cache[i] = cache[i - 1] + cache[i - 2];
        }

        return cache[num];
    }

    public static void main(String[] args) {

        int num = 10;
        for(int i=0; i<num; i++) {
            System.out.println(recursive(i));;
        }

        System.out.println(cacheFibo(10));
    }
}
