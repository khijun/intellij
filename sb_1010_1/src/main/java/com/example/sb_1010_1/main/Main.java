package com.example.sb_1010_1.main;

import com.example.sb_1010_1.config.AppCtx;
import com.example.sb_1010_1.config.AppCtx2;
import com.example.sb_1010_1.spring.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx2.class);

        Client client = ctx.getBean(Client.class);
        client.send();

        ctx.close();
    }
}
