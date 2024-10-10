package com.example.sb_1010_1.spring;

import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Setter
public class Client2{

    private String host;

    public void send(){
        System.out.println("Client.send() to " + host);
    }

    public void connect(){
        System.out.println("Client2.connect() called");
    }

    public void close(){
        System.out.println("Client2.close() called");
    }
}
