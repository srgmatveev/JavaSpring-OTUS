package ru.otus.example.advancedconfigurationdemo.lifecycle;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.otus.example.advancedconfigurationdemo.services.AbstractGreetingServiceImpl;
import ru.otus.example.advancedconfigurationdemo.services.SingletonGreetingServiceImpl;

import java.lang.reflect.Field;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(CustomLifeCycleBean.class)) {
            System.out.println("Шаг #5: BeanPostProcessor.postProcessBeforeInitialization\n");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(CustomLifeCycleBean.class)) {
            System.out.println("Шаг #8: BeanPostProcessor.postProcessAfterInitialization\n");
        }

        if (bean.getClass().equals(SingletonGreetingServiceImpl.class)) {
            updateGreeting(bean);
        }
        return bean;
    }

    @SneakyThrows
    private void updateGreeting(Object bean) {
        Class aClass = AbstractGreetingServiceImpl.class;
        Field firstGreetingField = aClass.getDeclaredField("firstGreeting");
        Field reGreetingField = aClass.getDeclaredField("reGreeting");

        firstGreetingField.setAccessible(true);
        reGreetingField.setAccessible(true);
        firstGreetingField.set(bean, "Доброго времени суток господа");
        reGreetingField.set(bean, "Это снова вы! Какой приятный сюрприз!");
    }
}
