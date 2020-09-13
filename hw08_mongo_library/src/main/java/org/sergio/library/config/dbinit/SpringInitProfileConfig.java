package org.sergio.library.config.dbinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
public class SpringInitProfileConfig {
    @Autowired
    DatasourceConfig datasourceConfig;

    @Bean
    public DatasourceConfig SetDatasourceConfig() {
        datasourceConfig.setup();
        return datasourceConfig;
    }
}
