package clazz;

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

        for(int i=0; i<dat.length; i++) {
            sum += dat[i].height;
        }

        return sum / dat.length;
    }

    static void distVision(PhyscData[] dat, int[] dist) {
       int i = 0;

       dist[i] = 0;

        for(i=0; i<dat.length; i++) {
            if(dat[i].vision >= 0.0 && dat[i].vision <= 2.0) {
                dist[(int)dat[i].vision * 10]++;
            }
        }

    }
}