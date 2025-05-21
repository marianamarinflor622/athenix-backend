package com.athenix.athenix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AthenixApplication {
    public static final String DB_URL = "DB_URL";
    public static final String DB_USER = "DB_USER";

    public AthenixApplication() {}

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        if (dotenv.get("DB_URL") == null || dotenv.get("DB_USER") == null) {
            System.err.println("⚠️ Variables de entorno esenciales no están definidas correctamente.");
        }

        System.setProperty("DB_URL", dotenv.get("DB_URL", ""));
        System.setProperty("DB_USER", dotenv.get("DB_USER", ""));
        System.setProperty("DB_PASS", dotenv.get("DB_PASS", ""));
        System.setProperty("spring.application.name", dotenv.get("SPRING_APPLICATION_NAME", "athenix"));

        String activeProfile = dotenv.get("SPRING_PROFILES_ACTIVE");
        if (activeProfile != null && !activeProfile.isBlank()) {
            System.setProperty("spring.profiles.active", activeProfile);
        }

        SpringApplication.run(AthenixApplication.class, args);
    }
}
