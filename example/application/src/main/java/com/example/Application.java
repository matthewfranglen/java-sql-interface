package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) { }

        SpringApplication.run(Application.class, args);
    }

}
