package clazz;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
// 신체검사 데이터 배열에서 검색 (시력)

// 신체검사 데이터
class PData implements Comparator<PData> {

    private String name; // 이름
    private int height; // 키
    private double vision; // 시력

    // 생성자
    public PData(String name, int height, double vision) {
        this.name = name;
        this.height = height;
        this.vision = vision;
    }

    public PData(double vision) {
        this.vision = vision;
    }

    public PData() {

    }

    // 문자열을 반환합니다.
    public String toString() {
        return name + " " + height + " " + vision;
    }

    // 시력 내림차순용 comparator
    @Override
    public int compare(PData o1, PData o2) {
        return (o1.vision > o2.vision) ? 1 : (o1.vision < o2.vision) ? -1 : 0;
    }
}

public class Compare {

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        PData[] x = { // 배열의 요소는 시력순이지 않으면 안됩니다.
                new PData("이나령", 162, 0.3),
                new PData("유지훈", 168, 0.4),
                new PData("전서현", 173, 0.7),
                new PData("김한결", 169, 0.8),
                new PData("이호연", 174, 1.2),
                new PData("홍준기", 171, 1.5),
                new PData("이수민", 175, 2.0),};

        double vision = 0.3; // 키 값을 입력 받음

        int idx = Arrays.binarySearch(x, // 배열 x에서
                new PData(vision),  // 키가 vision인 요소를
                new PData()
        );

        if (idx < 0)
            System.out.println("그 값의 요소가 없습니다.");
        else {
            System.out.println("그 값은 " + "x[" + idx + "]에 있습니다.");
            System.out.println("데이터：" + x[idx]);
        }
    }
}