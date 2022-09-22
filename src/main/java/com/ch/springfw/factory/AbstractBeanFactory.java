package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.single.DefaultSingletonBeanFactory;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanFactory implements BeanFactory {

    @Override
    public Object getBean(String name,Object[] args) throws BeanException {
        Object bean = getSingleton(name);
        if(bean!=null){
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        bean=createBean(name, beanDefinition,args);

        return bean;
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return getBean(name,null);
    }

    abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;
    abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws BeanException;
}
