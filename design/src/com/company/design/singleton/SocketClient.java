package com.company.design.singleton;

// Singleton Pattern
// 서로 자원을 공유할 경우 주로 사용 ex. 프린터, 소켓, DB Connect, Bean
// 여러 개 존재 해서 메모리 낭비될 필요가 없을 경우
// 단 하나만 존재해도 될 경우, 되어야 할 경우
public class SocketClient {

    private static SocketClient socketClient;

    private SocketClient() {}

    public static SocketClient getInstance() {
        if(socketClient == null) {
           socketClient = new SocketClient();
        }
        return socketClient;
    }

    public void connect() {
        System.out.println("connect");
    }
}
