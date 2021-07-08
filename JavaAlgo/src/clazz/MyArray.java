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

    }

    public void insertElement(int position, int num) {

    }

    public int removeElement(int position) {

        return 0;
    }

}
