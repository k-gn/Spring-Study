package algo;

// 이진 탐색
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

        // 가운데 값을 찾은 후 비교 -> 다를 경우 더 큰수인지 작은 수 인지에 따라 왼쪽 오른쪽 기준값을 변경 -> 변경된 기준에 따라 가운데 값도 다시 달라진다.
        // -> 반복하여 찾는 수를 구한다.
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
