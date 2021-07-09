package clazz;

class Id {

    // static 정적 멤버
    // 젼역 변수 개념
    // 클래스 로더가 클래스 로딩 시 스태틱 메모리 영역에 생성된다.
    // 클래스의 모든 객체에 공통으로 사용되는 멤버
    // 클래스를 new 로 생성하지 않아도 호출 가능

    private static int counter = 0;
    private int id;

    public Id() {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public static int getCounter() {
        return counter;
    }

}

public class IdTest {

    public static void main(String[] args) {

        Id a = new Id();
        Id b = new Id();

        System.out.println(a.getId());
        System.out.println(b.getId());
    }
}
