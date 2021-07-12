package clazz;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insertNode(5);
        tree.insertNode(8);
        tree.insertNode(7);
        tree.insertNode(10);
        tree.insertNode(9);
        tree.insertNode(11);


        if (tree.removeNode(10)) {
            System.out.println("노드 삭제");
        }

    }
}
