package com.yffd.bcap.uamc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class UamcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UamcApplication.class, args);
    }

}
