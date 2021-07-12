package clazz;

class Node {
    int value;
    Node leftChild;
    Node rightChild;

    public Node(int value) {
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}

// 이진 탐색 트리
public class BinarySearchTree {

    Node rootNode = null;

    public void insertNode(int element) {

        if(rootNode == null) {
            rootNode = new Node(element);
        }else {
            Node head = rootNode; // 비어있는지 확인하기 위한 용도의 변수
            Node current = null; // 해당 값을 왼쪽 또는 오른쪽에 넣기 위한 변수

            while(true) {
                current = head;

                if(head.value > element) {
                    head = head.leftChild;

                    if(head == null) {
                        current.leftChild = new Node(element);
                        break;
                    }
                }else {
                    head = head.rightChild;

                    if(head == null) {
                        current.rightChild = new Node(element);
                        break;
                    }
                }
            }
        }
    }

    public boolean removeNode(int element) {

        Node removeNode = rootNode; // 삭제할 노드
        Node parentOfRemoveNode = null; // 삭제할 노드의 부모

        while(removeNode.value != element) {

            parentOfRemoveNode = removeNode;

            if(removeNode.value > element) {
                removeNode = removeNode.leftChild;
            }else {
                removeNode = removeNode.rightChild;
            }

            if (removeNode == null)
                return false;
        }

        /* 자식 노드가 모두 없을 때 */
        if (removeNode.leftChild == null && removeNode.rightChild == null) {
            if(removeNode == rootNode) {
                rootNode = null;
            }else if (removeNode == parentOfRemoveNode.rightChild) {
                parentOfRemoveNode.rightChild = null;
            }else {
                parentOfRemoveNode.leftChild = null;
            }
        }

        /* 오른쪽 자식 노드만 존재하는 경우 */
        else if (removeNode.leftChild  == null) {
            if (removeNode == rootNode) {
                rootNode = removeNode.rightChild;
            }else if (removeNode == parentOfRemoveNode.rightChild) {
                /*
                 * 삭제 대상의 오른쪽 자식 노드를 삭제 대상 위치에 둔다.
                 */
                parentOfRemoveNode.rightChild = removeNode.rightChild;
            } else {
                parentOfRemoveNode.leftChild = removeNode.rightChild;
            }
        }
        /* 왼쪽 자식 노드만 존재하는 경우 */
        else if (removeNode.rightChild == null) {
            if (removeNode == rootNode) {
                rootNode = removeNode.leftChild;
            } else if (removeNode == parentOfRemoveNode.rightChild) {
                parentOfRemoveNode.rightChild = removeNode.leftChild;
            } else {
                /*
                 * 삭제 대상의 왼쪽 자식을 삭제 대상 위치에 둔다.
                 */
                parentOfRemoveNode.leftChild = removeNode.leftChild;
            }
        }
        /*
         * 두 개의 자식 노드가 존재하는 경우
         * 삭제할 노드의 왼쪽 서브 트리에 있는 가장 큰 값 노드를 올리거나
         * 오른쪽 서브 트리에 있는 가장 작은 값 노드를 올리면 된다.
         * 구현 코드는 2번째 방법을 사용한다.
         */
        else {
            /* 삭제 대상 노드의 자식 노드 중에서 대체될 노드(replaceNode)를 찾는다. */
            Node parentOfReplaceNode = removeNode; // 가장 작은값의 부모

            Node replaceNode = parentOfReplaceNode.rightChild; // 가장 작은값

            while (replaceNode.leftChild != null) {
                /* 가장 작은 값을 찾기 위해 왼쪽 자식 노드로 탐색한다. */
                parentOfReplaceNode = replaceNode;
                replaceNode = replaceNode.leftChild;
            }

            if (replaceNode != removeNode.rightChild) {
                /* 가장 작은 값을 선택하기 때문에 대체 노드의 왼쪽 자식은 빈 노드가 된다. */
                parentOfReplaceNode.leftChild = null;

                /* 대체할 노드의 오른쪽 자식 노드를 삭제할 노드의 오른쪽으로 지정한다. */
                replaceNode.rightChild = removeNode.rightChild;
            }

            /* 삭제할 노드가 루트 노드인 경우 대체할 노드로 바꾼다. */
            if (removeNode == rootNode) {
                rootNode = replaceNode;
            } else if (removeNode == parentOfRemoveNode.rightChild) {
                parentOfRemoveNode.rightChild = replaceNode;
            } else {
                parentOfRemoveNode.leftChild = replaceNode;
            }

            /* 삭제 대상 노드의 왼쪽 자식을 잇는다. */
            replaceNode.leftChild = removeNode.leftChild;
        }

        return true;
    }

}
