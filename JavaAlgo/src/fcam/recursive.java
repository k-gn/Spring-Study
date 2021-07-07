package fcam;

import java.util.Stack;

// 재귀
public class recursive {

    public static void recursiveDef(int data) {
        if(data < 0) {
            System.out.println("ended");
        }else {
            System.out.println("data : " + data);
            recursiveDef(data - 1);
            System.out.println("returned " + data);
        }
    }

    public static void main(String[] args) {
        recursiveDef(5);
    }
}
