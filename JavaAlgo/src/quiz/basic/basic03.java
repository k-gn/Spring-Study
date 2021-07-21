package quiz.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class basic03 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("n : ");
        int n = Integer.parseInt(br.readLine());

        int count = 1;
        Stack<Integer> stack = new Stack<>();
        List<Character> characterList = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int data = Integer.parseInt(br.readLine());

            while(count <= data) {
                stack.push(count);
                count++;
                characterList.add('+');
            }
            System.out.println("stack : " + stack);

            if(stack.peek() == data) {
                stack.pop();
                characterList.add('-');
            }else {
                System.out.println("No");
                System.exit(0);
            }
        }

        System.out.println(characterList);
    }
}
