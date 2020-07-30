package ru.otus.example.advancedconfigurationdemo.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.example.advancedconfigurationdemo.services.PrototypeGreetingServiceImpl;
import ru.otus.example.advancedconfigurationdemo.services.SessionGreetingServiceImpl;
import ru.otus.example.advancedconfigurationdemo.services.SingletonGreetingServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AdvancedConfigurationDemoApplicationTests {

/*
	@Configuration
	static class NestedConfiguration {
		@Bean
		SingletonGreetingServiceImpl singletonGreetingService() {
			return new SingletonGreetingServiceImpl();
		}
	}
*/

    @TestConfiguration
    static class NestedTestConfiguration {
        @Bean
        public TempBeanClass tempBeanClass(){
            return new TempBeanClass();
        }
    }

    @Autowired private SingletonGreetingServiceImpl singletonGreetingService;

    @Autowired private TempBeanClass tempBeanClass;

    @Test
    void contextLoads() {
    }

}
