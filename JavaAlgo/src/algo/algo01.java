package algo;

public class algo01 {
    public static void main(String[] args) {
        System.out.println("hello");

        int[] numbers = {10, 55, 23, 2, 79, 101, 16, 82, 30, 45};

        int min = numbers[0];
        int max = numbers[0];
        int minIdx = 0;
        int maxIdx = 0;

        for(int i=0; i<numbers.length; i++) {
            if(min > numbers[i]) {
                min = numbers[i];
                minIdx = i;
            }

            if(max < numbers[i]) {
                max = numbers[i];
                maxIdx = i;
            }
        }

        System.out.println("배열의 최대값 : " + max);
        System.out.println("배열의 최대값 인덱스 : " + maxIdx);
        System.out.println("배열의 최소값 : " + min);
        System.out.println("배열의 최소값 인덱스 : " + minIdx);

    }
}
