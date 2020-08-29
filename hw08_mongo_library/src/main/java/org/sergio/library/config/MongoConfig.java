package org.sergio.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import javax.annotation.PostConstruct;

@Configuration
@Profile(value = "prod")
public class MongoConfig {
    @Autowired
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    /*@PostConstruct
    public void initIndexes() {

        mongoTemplate.indexOps("authors") // collection name string or .class
                .ensureIndex(
                        new Index().on("surName", Sort.Direction.ASC)
                );


        mongoTemplate.indexOps("authors") // collection name string or .class
                .ensureIndex(
                        new Index().on("name", Sort.Direction.ASC)
                );

    }*/

    @Bean
    MongoTransactionManager transactionManager() {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }
}
