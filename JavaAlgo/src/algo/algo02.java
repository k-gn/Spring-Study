package algo;

public class algo02 {
    public static void main(String[] args) {
        System.out.println("hello");

        int[] numbers = {12, 25, 31, 48, 54, 66, 70, 83, 95, 108};

        int target = 70;
        int left = 0;
        int right = numbers.length - 1;
        int mid = (left + right) / 2;

        int index = mid;
        boolean flag = false;

        while (left <= right) {
            if(numbers[mid] == target) {
                flag = true;
                break;
            }else if(target < numbers[mid]) {
                right = mid - 1;
            }else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }

        if(!flag) {
            System.out.println(target + "은 없는 숫자입니다.");
        }else {
            System.out.println(target + "의 위치는 : " + mid);
        }

    }
}
