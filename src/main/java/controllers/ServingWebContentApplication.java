package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        // todo i don't know meaning of this string
        // but without it I can not connect to DB through the web UI
        System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
