package ru.otus.example.advancedconfigurationdemo.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("Friend1")
@ConditionalOnProperty(name = "condition.friend", havingValue = "Алиса")
public class FriendAlisa implements Friend {
    @Override
    public String getName() {
        return "Алиса";
    }
}
