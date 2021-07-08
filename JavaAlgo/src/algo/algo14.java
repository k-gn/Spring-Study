package algo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class algo14 {

    static int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }, // 평년
            { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }, // 윤년
    };

    // 서기 year년은 윤년인가? (윤년：1／평년：0)
    static int isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
    }

    static int dayOfYear(int y, int m, int d) {
        int days = d;
        for(int i=1; i<m; i++) {
            days += mdays[isLeap(y)][i - 1];
        }

//        while(--m > 0) {
//            d += mdays[isLeap(4)][m - 1];
//        }
        return days;
    }

    static int lastDayOfYear(int y, int m, int d) {
        int days = d;
        for(int i=1; i<m; i++) {
            days += mdays[isLeap(y)][i - 1];
        }
        return 365 + isLeap(y) - days;
    }

//    해의 경과일수
    public static void main(String[] args) throws IOException {

        int year = 2021;
        int month = 1;
        int day = 8;
        System.out.println("해의 경과일수 : " + dayOfYear(year, month, day));

        // 2차원 배열
//        int[][] x = new int[2][4];
//        x[0][1] = 37;
//        for(int i=0; i< x.length; i++) {
//            for(int j=0; j<x[0].length; j++) {
//
//            }
//        }
        // 3차원 배열
//        long[][][] y = new long[2][3][4];



    }

}
