package ru.otus.example.advancedconfigurationdemo.lifecycle;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import ru.otus.example.advancedconfigurationdemo.model.FriendArnold;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("Шаг #1: BeanFactoryPostProcessor.postProcessBeanFactory\n");
        for (String beanName : beanFactory.getBeanDefinitionNames()) {

            if (beanName.equals("Friend1")) {
                BeanDefinition d = beanFactory.getBeanDefinition(beanName);
                d.setBeanClassName(FriendArnold.class.getName());
                ((ScannedGenericBeanDefinition) d).addMetadataAttribute(new BeanMetadataAttribute("className", FriendArnold.class.getName()));
                d.setAutowireCandidate(true);
            }
        }
    }
}
