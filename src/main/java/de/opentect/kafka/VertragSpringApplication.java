package de.opentect.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VertragSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartnerSpringApplication.class, args);
    }
}