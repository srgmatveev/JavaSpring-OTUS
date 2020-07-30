package ru.otus.example.advancedconfigurationdemo.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(LifeCycleConfigCondition.class)
public class LifeCycleConfig {

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public CustomLifeCycleBean lifeCircleBean() {
        return new CustomLifeCycleBean();
    }

    @Bean
    public CustomBeanFactoryPostProcessor beanFactoryPostProcessor(){
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public CustomBeanPostProcessor beanPostProcessor(){
        return new CustomBeanPostProcessor();
    }
}
