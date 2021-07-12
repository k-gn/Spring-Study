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

    public HashTable(int size) {
        table = new LinkedList[size];
    }

    Long getHashCode(String key) { // 해쉬 코드 생성
        Long hashCode = 0L;

        for (char c : key.toCharArray()) {
            hashCode += (long) c;
        }

        return hashCode;
    }

    public int getIndex(Long hashCode) { // 해쉬 코드를 사용하여 인덱스 생성
        return (int) (hashCode % table.length);
    }

    Node searchNode(int index, String key) {
        LinkedList<Node> indexedList = table[index];

        for (Node n : indexedList) {
            if (n.key == key) {
                return n;
            }
        }

        return null;
    }

    public String get(String key) {
        Long hashCode = getHashCode(key);
        int index = getIndex(hashCode);

        Node searched = searchNode(index, key);

        if (searched == null) {
            return "";
        } else {
            return searched.value;
        }
    }

    public void put(String key, String value) {
        Long hashCode = getHashCode(key);
        int index = getIndex(hashCode);

        if (table[index] == null) {
            table[index] = new LinkedList<Node>();
            table[index].add(new Node(key, value));
        } else {
            Node searched = searchNode(index, key);

            if (searched != null) {
                searched.value = value;
            } else {
                table[index].add(new Node(key, value));
            }
        }
    }
}
