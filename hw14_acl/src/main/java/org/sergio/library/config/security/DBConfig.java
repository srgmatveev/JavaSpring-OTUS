package org.sergio.library.config.security;

import org.springframework.context.annotation.Profile;

@Profile("security_init")
public interface DBConfig {
 void setup();
}
