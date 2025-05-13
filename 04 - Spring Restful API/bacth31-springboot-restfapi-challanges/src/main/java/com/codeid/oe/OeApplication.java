package com.codeid.oe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OeApplication.class, args);
    }

}
