package org.sergio.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
@Profile(value = "dev")
public class MongoConfig {
    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Bean
    MongoTransactionManager transactionManager() {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}
