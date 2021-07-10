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

    public MyListNode insertElement(int position, String data) {

        MyListNode temp = head;
        MyListNode newNode = new MyListNode(data);

        if(position < 0 || position > count ){
            System.out.println("추가 할 위치 오류 입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
            return null;
        }

        if(position == 0) {
            newNode.next = head;
            head = newNode;
        }else {
            MyListNode preNode = null;
            for(int i=0; i<position; i++) {
                preNode = temp; // 삽입할 자리 이전 값
                temp = temp.next; // 삽입할 자리 값 (position 자리값)
            }

            newNode.next = temp;
            preNode.next = newNode;
        }
        count++;

        return newNode;
    }

    public MyListNode removeElement(int position) {

        MyListNode temp = head;

        // 1 -> 2 -> 3 -> 4
        if(position >= count){
            System.out.println("삭제 할 위치 오류입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
            return null;
        }

        if(position == 0){  //맨 앞을 삭제하는
            head = temp.next;
        }else {
            MyListNode preNode = null;
            for(int i=0; i<position; i++) {
                preNode = temp; // 지울 값 바로 이전 값
                temp = temp.next; // 이값이 지우고자 하는 값 (position 자리 값)
            }
            preNode.next = temp.next;
        }
        count--;

        return temp;
    }

    public String getElement(int position) {

        MyListNode tempNode = head;

        if(position >= count ){
            System.out.println("검색 위치 오류 입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
            return new String("error");
        }

        if(position == 0){  //맨 인 경우
            return head.getData();
        }else {
            for(int i=0; i<position; i++) {
                tempNode = tempNode.next;
            }
        }


        return tempNode.getData();
    }

    public MyListNode getNode(int position) {

        MyListNode tempNode = head;

        if(position >= count ){
            System.out.println("검색 위치 오류 입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
            return null;
        }

        if(position == 0){  //맨 인 경우

            return head;
        }

        for(int i=0; i<position; i++){
            tempNode = tempNode.next;

        }

        return tempNode;
    }

    public void removeAll() {
        head = null;
        count = 0;
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
