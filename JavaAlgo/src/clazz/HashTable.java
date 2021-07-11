package clazz;

import java.util.LinkedList;

public class HashTable { // Seperate Chaining
    class Node {
        String key;
        String value;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] table;
}
