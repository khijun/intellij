package com.example.sb_1010_1.config;

import com.example.sb_1010_1.spring.Client;
import com.example.sb_1010_1.spring.Client2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx2 {
	@Bean
	@Scope("prototype")
	public Client client(){
		Client client = new Client();
		client.setHost("host");
		return client;
	}
	@Bean(initMethod = "connect", destroyMethod = "close")
	public Client2 client2(){
		Client2 client = new Client2();
		return client;
	}
}