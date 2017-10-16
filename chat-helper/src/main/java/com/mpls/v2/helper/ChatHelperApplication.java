package com.mpls.v2.helper;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class ChatHelperApplication {

	public static void main(String[] args) {
		System.setProperty ( "spring.devtools.restart.enabled" , "true" );
		SpringApplication.run(ChatHelperApplication.class, args);
	}
}
