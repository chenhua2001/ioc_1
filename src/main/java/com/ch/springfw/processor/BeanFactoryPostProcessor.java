package com.ch.springfw.processor;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.ConfigurableListableBeanFactory;
/*
* 提供修改BeanDefinition的机制
* */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
