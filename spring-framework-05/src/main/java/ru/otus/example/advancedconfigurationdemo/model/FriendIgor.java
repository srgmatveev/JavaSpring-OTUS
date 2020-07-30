package ru.otus.example.advancedconfigurationdemo.model;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component("Friend1")
@ConditionalOnProperty(name = "condition.friend", havingValue = "Игорь")
public class FriendIgor implements Friend {

    @Override
    public String getName() {
        return "Игорь";
    }
}
