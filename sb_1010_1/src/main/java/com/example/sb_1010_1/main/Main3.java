package com.example.sb_1010_1.main;

import com.example.sb_1010_1.config.AppCtx2;
import com.example.sb_1010_1.spring.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main3 {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx2.class);

        Client client1 = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        System.out.println("client1 == client2 : " + (client1 == client2));

        ctx.close();
    }
}
