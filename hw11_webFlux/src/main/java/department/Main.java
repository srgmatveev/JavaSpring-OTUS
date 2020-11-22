package department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.lang.invoke.MethodHandles;

@EnableMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(MethodHandles.lookup().lookupClass(), args);
    }
}
