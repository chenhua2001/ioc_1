package com.ch.springfw.factory;

import com.ch.springfw.expection.BeanException;

public interface BeanFactory {
    public Object getBean(String name) throws BeanException;
    public Object getBean(String name,Object[] args) throws BeanException;
    <T> T getBean(String name, Class<T> requiredType) throws BeanException;
}
