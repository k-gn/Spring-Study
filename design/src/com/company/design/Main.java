package com.company.design;

import com.company.design.adapter.Cleaner;
import com.company.design.adapter.Electronic110V;
import com.company.design.adapter.HairDryer;
import com.company.design.adapter.SocketAdapter;
import com.company.design.decorator.A3;
import com.company.design.decorator.Audi;
import com.company.design.decorator.ICar;
import com.company.design.facade.FTP;
import com.company.design.facade.Reader;
import com.company.design.facade.SftpClient;
import com.company.design.facade.Writer;
import com.company.design.observer.Button;
import com.company.design.observer.IButtonListener;
import com.company.design.proxy.AopBrowser;
import com.company.design.proxy.Browser;
import com.company.design.proxy.BrowserProxy;
import com.company.design.proxy.IBrowser;
import com.company.design.singleton.AClazz;
import com.company.design.singleton.BClazz;
import com.company.design.singleton.SocketClient;
import com.company.design.strategy.*;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) {

        /*
        AClazz aClazz = new AClazz();
        BClazz bClazz = new BClazz();

        SocketClient aClient = aClazz.getSocketClient();
        SocketClient bClient = bClazz.getSocketClient();

        System.out.println("두 개의 객체가 동일한가?");
        System.out.println(aClient.equals(bClient));
        */
        /*
        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();
        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);
        */

        //Browser browser = new Browser("www.naver.com");
        //browser.show();
        /*
        IBrowser browser = new BrowserProxy("www.naver.com");
        browser.show();
        browser.show();
        browser.show();
        browser.show();
        browser.show();
         */

        // 시간체크 (동시성 문제로 Atomic 사용)
        /*
        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser browser = new AopBrowser("www.naver.com",
                () -> {
                    System.out.println("before");
                    start.set(System.currentTimeMillis());
                },
                () -> {
                    long now = System.currentTimeMillis();
                    end.set(now - start.get());
                });
        browser.show();
        System.out.println("loading time : " + end.get());
        browser.show();
        System.out.println("loading time : " + end.get());
         */

        /* 기본은 유지하고 차종에 따라 가격에 변화를 준다.
        ICar audi = new Audi(1000);
        audi.showPrice();

        ICar audi3 = new A3(audi, "A3");
        audi3.showPrice();
         */

        /*
        Button button = new Button("버튼");
        button.addListener(new IButtonListener() {
            @Override
            public void clickEvent(String event) {
                System.out.println(event);
            }
        });
        button.click("메시지 전달1");
        button.click("메시지 전달2");
        button.click("메시지 전달3");
         */
        /*
        FTP ftpClient = new FTP("www.foo.co.kr", 22, "/home/etc");
        ftpClient.connect();
        ftpClient.moveDirectory();

        Writer writer = new Writer("text.tmp");
        writer.fileConnect();
        writer.write();

        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        ftpClient.disConnect();
        writer.fileDisConnect();
        reader.fileDisConnect();

        SftpClient sftpClient = new SftpClient("www.foo.co.kr", 22, "/home/etc", "text.tmp");
        sftpClient.connect();
        sftpClient.write();
        sftpClient.read();
        sftpClient.disConnect();
         */

        Encoder encoder = new Encoder();

        EncodingStrategy base64 = new Base64Strategy();
        EncodingStrategy normal = new NormalStrategy();

        String message = "Hello Java";

        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);

        System.out.println(base64Result);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(new AppendStrategy());
        System.out.println(encoder.getMessage(message));

    }

    // 콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
