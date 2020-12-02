package org.sergio.library.service;

import org.sergio.library.domain.security.SecUser;
import org.sergio.library.repository.security.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<SecUser> userList = repo.findByLogin(userName);
        if (userList == null || userList.size() == 0)
            throw new UsernameNotFoundException("Unknown user: " + userName);
        SecUser myUser = userList.get(0);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        UserDetails user = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
        //System.out.println("hello" + user.toString());
        return user;
    }
}
