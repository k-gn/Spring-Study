package com.company.design.adapter;

// Adapter Pattern
// 자기 자신의 형태를 바꾸지 않고 중간에 인터페이스를 맞춘 어댑터를 만들어 연결
// 호환성이 없는 기존 클래스의 인터페이스를 변환하여 재사용할 수 있게한다.
// 개방폐쇄 원칙을 따른다.
public class SocketAdapter implements Electronic110V {

    private Electronic220V electronic220V;

    public SocketAdapter(Electronic220V electronic220V) {
        this.electronic220V = electronic220V;
    }

    @Override
    public void powerOn() {
        electronic220V.connect();
    }
}
