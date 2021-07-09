package clazz;

// 직접 배열 구현해보기
public class MyArray {

    int[] intArr;   	//int array
    int count;  		//개수

    public int ARRAY_SIZE;
    public static final int ERROR_NUM = -999999999;

    public MyArray()
    {
        count = 0;
        ARRAY_SIZE = 10;
        intArr = new int[ARRAY_SIZE];
    }

    public MyArray(int size)
    {
        count = 0;
        ARRAY_SIZE = size;
        intArr = new int[size];
    }

    public void addElement(int num) {

        if(count >= ARRAY_SIZE) {
            System.out.println("Not Enough Memory");
            return;
        }

        intArr[count++] = num;
    }

    public void insertElement(int position, int num) {

        if(count >= ARRAY_SIZE) {
            System.out.println("Not Enough Memory");
            return;
        }

        // 1 2 3 4 5
        if(position < 0 || position > count) {
            System.out.println("Insert Position Error");
            return;
        }

        // 10 20 30 => intArr[1] = 50
        // position = 1
        // num = 50
        // count = 3
        for(int i=count - 1; i>=position; i--) {
            intArr[i + 1] = intArr[i];
        }

        intArr[position] = num;
        count++;

    }

    public int removeElement(int position) {

        int ret = ERROR_NUM;

        if(isEmpty()) {
            System.out.println("There is no Element");
            return ret;
        }

        // 1 2 3 4 5 -> count = 5
        if(position < 0 || position >= count) {
            System.out.println("Remove Position Error");
            return ret;
        }

        ret = intArr[position];

        for (int i=position; i<count; i++) {
            intArr[i] = intArr[i + 1];
        }

        count--;
        return ret;
    }

    public int getSize() {

        return count;
    }

    public boolean isEmpty() {

        if(count == 0) {
            return true;
        }
        return false;
    }

    public int getElement(int position) {

        if(isEmpty()) {
            System.out.println("There is no Element");
            return ERROR_NUM;
        }

        // 1 2 3 4 5
        if(position < 0 || position > count - 1) {
            System.out.println("Position Error");
            return ERROR_NUM;
        }

        return intArr[position];
    }

    public void printAll() {
        if(count == 0){
            System.out.println("출력할 내용이 없습니다.");
            return;
        }

        for(int i=0; i<count; i++){
            System.out.println(intArr[i]);
        }

    }

    public void removeAll() {
        for(int i=0; i<count; i++){
            intArr[i] = 0;
        }
        count = 0;
    }

}
