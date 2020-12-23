package org.sergio.library.config.security;

import org.sergio.library.domain.security.SecUser;
import org.sergio.library.repository.security.UserRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Component(value = "dbInit")
@Profile("security_init")
@Configuration
@ConfigurationProperties(prefix = "users")
public class DBInit implements DBConfig {
    final private UserRepo repo;

    @Value("${admin.username}")
    private String adminUserName;
    @Value("${admin.password}")
    private String password;
    @Value("${admin.role}")
    private String role;

    @Value("${users.role}")
    private String usersRole;


    private Map<String, String> userMap = new HashMap<>();

    public Map<String, String> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, String> userMap) {
        this.userMap = userMap;
    }

    public DBInit(@Qualifier("userRepo") UserRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void setup() {
        //List<SecUser> users = repo.findByLogin(adminUserName);
        //repo.deleteAll(users);
        repo.deleteAll();
        addUser(adminUserName, password, role);


        userMap.forEach((sUser, passwd) -> {
            addUser(sUser, passwd, usersRole);
        });

    }

    private void addUser(String name, String password, String role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String val = encoder.encode(password);
        SecUser user = new SecUser();
        user.setLogin(name);
        user.setPassword(val);
        user.setRole(role);
        repo.save(user);
    }



}
