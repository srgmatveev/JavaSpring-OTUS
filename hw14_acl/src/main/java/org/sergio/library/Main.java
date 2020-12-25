package org.sergio.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
@EnableCaching
@EnableMongoRepositories(basePackages = "org.sergio.library.repository")
@EnableJpaRepositories(basePackages = "org.sergio.library.secRepo.acl")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(MethodHandles.lookup().lookupClass(), args);}
}
