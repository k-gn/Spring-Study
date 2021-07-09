package clazz;

public class MyLinkedList {

    private MyListNode head;
    int count;

    public MyLinkedList()
    {
        head = null;
        count = 0;
    }

    public MyListNode addElement( String data ) {

        MyListNode newNode;

        if(head == null) {
            newNode = new MyListNode(data);
            head = newNode;
        }else {
            newNode = new MyListNode(data);
            MyListNode node = head;
            while(node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
        count++;

        return newNode;
    }

    public MyListNode insertElement(int position, String data ) {

        return null;
    }

    public MyListNode removeElement(int position) {

        return null;
    }

    public String getElement(int position) {

        return null;
    }

    public MyListNode getNode(int position) {

        return null;
    }

    public void removeAll() {

    }

    public int getSize() {

        return count;
    }

    public void printAll() {
        if(count == 0){
            System.out.println("출력할 내용이 없습니다.");
            return;
        }

        MyListNode temp = head;
        while(temp != null){
            System.out.print(temp.getData());
            temp = temp.next;
            if(temp!=null){
                System.out.print("->");
            }
        }
        System.out.println("");
    }

    public boolean isEmpty() {
        if(head == null) return true;
        else return false;
    }

}
