package algo;

public class algo07 {

    public static void main(String[] args) {

//        int n = 5;
//        for(int i=0; i<n; i++) {
//            for(int j=0; j<n; j++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }

        System.out.print("  | ");
        for(int i=1; i<10; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        System.out.println("--+----------------------------");

        for(int i=1; i<10; i++) {

            System.out.print(i + " | ");
            for(int j=1; j<10; j++) {
                int num = i * j;
                if(num < 10) {
                    System.out.print(" " + i * j + " ");
                }else {
                    System.out.print(i * j + " ");
                }
            }
            System.out.println();
        }

    }

}
