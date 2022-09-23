package com.ch.springfw.factory.aware;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeanException;
}
