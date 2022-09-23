package com.ch.springfw.processor;

import com.ch.springfw.context.ApplicationContext;
import com.ch.springfw.factory.aware.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor{
    private final ApplicationContext  applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof ApplicationContextAware)
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
