package ru.egartech.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.egartech.*")
public class ProfileBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProfileBackendApplication.class, args);
    }

}
