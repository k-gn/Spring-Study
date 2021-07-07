package fcam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class stack {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("hello");
        stack.push("world");
        stack.forEach(System.out::println);
        if(!stack.isEmpty()) {
            stack.pop();
        }
        stack.forEach(System.out::println);
    }
}
