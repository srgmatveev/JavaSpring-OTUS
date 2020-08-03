package org.sergio.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.lang.invoke.MethodHandles;
import java.util.Locale;

@SpringBootApplication
public class Main {
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18/messages");
        return messageSource;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        ApplicationContext context = SpringApplication.run(Main.class);
    }

}
