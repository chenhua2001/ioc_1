package com.ch.springfw.factory.single;

import com.ch.springfw.factory.BeanFactory;

public interface SingletonBeanFactory {
    public Object getSingleton(String beanName);
    public void destroySingleton() throws Exception;
}
