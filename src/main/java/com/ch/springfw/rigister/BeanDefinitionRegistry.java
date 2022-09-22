package com.ch.springfw.rigister;

import com.ch.springfw.BeanDefinition;

public interface BeanDefinitionRegistry {
    public void addBeanDefinitionRegistry(String name, BeanDefinition beanDefinition);
    public boolean containsBeanDefinition(String name);
}
