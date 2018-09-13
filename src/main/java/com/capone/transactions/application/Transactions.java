package com.capone.transactions.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.capone.transactions")
public class Transactions {

	public static void main(String[] args) {
        SpringApplication.run(Transactions.class, args);
    }
}
