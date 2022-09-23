package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.processor.BeanPostProcessor;

import java.util.List;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory{
    BeanDefinition getBeanDefinition(String beanName) throws BeanException;
    void preInstantiateSingletons() throws BeanException;
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
