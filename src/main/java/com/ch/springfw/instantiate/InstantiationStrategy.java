package com.ch.springfw.instantiate;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String name, Object[] args) throws BeanException;
}
