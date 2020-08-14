package org.sergio.library.config.dbinit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DBInit implements DatasourceConfig{
    @Override
    public void setup() {
        System.out.println("hello");
    }
}
