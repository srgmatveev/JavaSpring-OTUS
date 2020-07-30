package ru.otus.example.advancedconfigurationdemo.lifecycle;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class LifeCycleConfigCondition extends AllNestedConditions {
    public LifeCycleConfigCondition() {
        super(ConfigurationPhase.PARSE_CONFIGURATION);
    }

    @ConditionalOnProperty(name = "spring.shell.interactive.enabled", havingValue = "false")
    static class ShellDisabledCondition {
    }

    @ConditionalOnProperty(name = "life-cycle-demo-enabled", havingValue = "true")
    static class ShowLifeCirclePropertyEnableddCondition {
    }
}
