package org.sergio.library.config.security;

import org.sergio.library.domain.security.SecUser;
import org.sergio.library.repository.security.UserRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "dbInit")
@Profile("security_init")
public class DBInit implements DBConfig {
    final private UserRepo repo;

    @Value("${admin.username}")
    private String adminUserName;
    @Value("${admin.password}")
    private String password;

    @Value("${admin.role}")
    private String role;


    private Map<String, String> simpleUsers = new HashMap<>();


    public DBInit(@Qualifier("userRepo") UserRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void setup() {
        List<SecUser> users = repo.findByLogin(adminUserName);
        repo.deleteAll(users);
        addUser(adminUserName, password, role);
        createSimpleUsers(simpleUsers);
        simpleUsers.forEach((sUser,passwd)->{
            addUser(sUser, passwd, "USER");
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

    private void createSimpleUsers(Map<String, String> users) {
        users.put("user1", "123456");
        users.put("user2", "password");

    }

}
