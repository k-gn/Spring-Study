package com.company.design.proxy;

public class AopBrowser implements IBrowser {

    private String url;
    private Html html;

    private Runnable before;
    private Runnable after;

    public AopBrowser(String url, Runnable before, Runnable after) {
        this.url = url;
        this.before = before;
        this.after = after;
    }

    @Override
    public Html show() {

        before.run();

        if(html == null) {
            this.html = new Html(url);
            System.out.println("aop loading html from : " + url);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        after.run();
        System.out.println("aop use cache html : " + url);

        return html;
    }
}
