package org.sergio.library.config.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("security_init")
public class SpringInitSecureConfig {
    private final DBConfig config;

    public SpringInitSecureConfig(@Qualifier("dbInit") DBConfig config) {
        this.config = config;
    }

    @Bean
    public DBConfig SetDatasourceConfig() {
        config.setup();
        return config;
    }

}
