package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");  // todo i don't know meaning of this string
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
