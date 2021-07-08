package clazz;

// n일뒤 or n일앞 날짜 구하기
public class YMD {

    private int y = 2021;
    private int m = 12;
    private int d = 30;

    static int[][] mdays = {{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, // 평년
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, // 윤년
    };

    static int isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ? 1 : 0;
    }

    public YMD() {}

    public YMD(int y, int m, int d) {
        this.y = y;
        this.m = m;
        this.d = d;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        if(d <= 0) {
            d = 1;
        }
        this.d = d;
    }

    YMD after(int n) {

        YMD ymd = new YMD(this.y, this.m, this.d);

        int nowDay = ymd.getD();
        int monthDay = mdays[isLeap(y)][m - 1];
        int afterDay = nowDay + n;
        System.out.println("afterDay : " + afterDay);
        System.out.println("monthDay : " + monthDay);
        System.out.println("nowDay : " + nowDay);
        if(afterDay > monthDay) {
            ymd.setD(afterDay - monthDay);
            if(ymd.getM() == 12) {
                ymd.setY(ymd.getY() + 1);
                ymd.setM(1);
            }else {
                ymd.setM(ymd.getM() + 1);
            }
        }else {
            ymd.setD(afterDay);
        }

        return ymd;
    }

    YMD before(int n) {

        YMD ymd = new YMD(this.y, this.m, this.d);

        int nowDay = ymd.getD();
        int beformMonthDay = mdays[isLeap(y)][m - 2 <= 0 ? 12 + (m-2) : m-2];
        int beforeDay = nowDay - n;
        if(beforeDay <= 0) {
            ymd.setD(beformMonthDay - (n - nowDay));
            if(ymd.getM() == 1) {
                ymd.setY(ymd.getY() - 1);
                ymd.setM(12);
            }else {
                ymd.setM(ymd.getM() - 1);
            }
        }else {
            ymd.setD(beforeDay);
        }

        return ymd;
    }

    public static void main(String[] args) {

        YMD ymd = new YMD();
        System.out.println("after : " + ymd.after(3));
//        System.out.println("before : " + ymd.before(3));
    }

    @Override
    public String toString() {
        return "YMD : " +
                "y = " + y +
                ", m = " + m +
                ", d = " + d;

    }
}
