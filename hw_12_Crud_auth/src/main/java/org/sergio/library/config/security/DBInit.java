package org.sergio.library.config.security;

import org.sergio.library.domain.security.SecUser;
import org.sergio.library.repository.security.UserRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value = "dbInit")
@Profile("security_init")
public class DBInit implements DBConfig{
    final private UserRepo repo;

    @Value("${admin.username}")
    private String adminUserName;
    @Value("${admin.password}")
    private String password;

    @Value("${admin.role}")
    private String role;


    public DBInit(@Qualifier("userRepo") UserRepo repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public void setup() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String val = encoder.encode(password);
        List<SecUser> users = repo.findByLogin(adminUserName);
        repo.deleteAll(users);
        SecUser user = new SecUser();
        user.setLogin(adminUserName);
        user.setPassword(val);
        user.setRole(role);
        repo.save(user);
    }
}
