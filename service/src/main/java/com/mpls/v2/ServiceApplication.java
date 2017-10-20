package com.mpls.v2;

import com.mpls.v2.service.utils.FileBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;

import javax.transaction.TransactionManager;

@SpringBootApplication(excludeName = "com.mpls.v2.service")
@Import(PersistenceApplication.class)
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{ServiceApplication.class, TransactionManager.class}, args);
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public FileBuilder fileBuilder() {
        return new FileBuilder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}