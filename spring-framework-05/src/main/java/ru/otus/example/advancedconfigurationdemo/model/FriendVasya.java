package ru.otus.example.advancedconfigurationdemo.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("Friend2")
@Profile("Вася")
public class FriendVasya implements Friend {
    @Override
    public String getName() {
        return "Вася";
    }
}
