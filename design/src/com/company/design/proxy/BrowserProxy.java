package com.company.design.proxy;

// Proxy Pattern
// 대리자 개념
// 대신 작업을 수행하고 전달하는 형태
// 개방 폐쇄 및 의존 역전 원칙
public class BrowserProxy implements IBrowser {

    private String url;
    private Html html;

    public BrowserProxy(String url) {
        this.url = url;
    }

    // Cache
    @Override
    public Html show() {

        if(html == null) {
            this.html = new Html(url);
            System.out.println("BrowserProxy loading html from : " + url);
        }
        System.out.println("BrowserProxy use cache html : " + url);
        return html;
    }
}
