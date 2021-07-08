package clazz;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PhysicalExamination {


}

class PhyscData {
    String name;
    int height;
    double vision;

    public PhyscData(String name, int height, double vision) {
        this.name = name;
        this.height = height;
        this.vision = vision;
    }

    static double aveHeight(PhyscData[] dat) {
        double sum = 0;

        for (int i = 0; i < dat.length; i++) {
            sum += dat[i].height;
        }

        return sum / dat.length;
    }

    static void distVision(PhyscData[] dat, int[] dist) {
        int i = 0;

        for (i = 0; i < dat.length; i++) {
            if (dat[i].vision >= 0.0 && dat[i].vision <= 2.0) {
                dist[(int) (dat[i].vision * 10)]++;
            }
        }

    }

    public static void main(String[] args) {
        PhyscData[] x = {
                new PhyscData("이나령", 162, 0.3),
                new PhyscData("전서현", 173, 0.7),
                new PhyscData("이수민", 175, 2.0),
                new PhyscData("홍준기", 171, 1.5),
                new PhyscData("유지훈", 168, 1.2),
                new PhyscData("이호연", 174, 1.2),
                new PhyscData("김한결", 169, 0.8),};

        int[] vdist = new int[21];
        DecimalFormat format = new DecimalFormat("0.0");
        System.out.println(" 평균 키 : " + format.format(aveHeight(x)));

        distVision(x, vdist);
        for(int i=0; i<21; i++) {

//            System.out.printf("%.1f ~ : %d명\n", i/10.0, vdist[i]);
            System.out.printf("%.1f ~ : ", i/10.0);
            for(int j=0; j<vdist[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}