package com.ch.springfw.factory;

import com.ch.springfw.factory.single.SingletonBeanFactory;
import com.ch.springfw.processor.BeanPostProcessor;

import java.util.List;

public interface ConfigurableFactory extends HierarchicalBeanFactory, SingletonBeanFactory {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    List<BeanPostProcessor> getBeanPostProcessor();
}
