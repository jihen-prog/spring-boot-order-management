package com.jihen.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Entry point for the Order Management Spring Boot application.
 */
public class OrderManagementApplication {
    /**
     * Starts the Spring application context and embedded web server.
     *
     * @param args command-line arguments passed to Spring Boot
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }
}
