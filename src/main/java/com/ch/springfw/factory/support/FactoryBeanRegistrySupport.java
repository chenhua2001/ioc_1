package com.ch.springfw.factory.support;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.BeanFactory;
import com.ch.springfw.factory.FactoryBean;
import com.ch.springfw.factory.single.DefaultSingletonBeanFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanFactory {
    private final Map<String,Object> factoryBeanObjectCache=new ConcurrentHashMap<>();
    protected  Object getCachedObjectForFactoryBean(String beanName){
        Object object=this.factoryBeanObjectCache.get(beanName);
        return (object!=NULL_OBJECT?object:null);

    }
    protected Object getCachedObjectForFactoryBean(String beanName, FactoryBean factory) throws BeanException {
        if(factory.isSingleton()){
            Object object=this.factoryBeanObjectCache.get(beanName);
            if(null==object){
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName,(object==null?NULL_OBJECT:object));
            }
            return (object!=NULL_OBJECT?object:null);
        }else {
            return doGetObjectFromFactoryBean(factory,beanName);
        }
    }
    private Object doGetObjectFromFactoryBean(final FactoryBean factory,final String beanName) throws BeanException {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeanException("FactoryBean getObject ["+beanName+"]fail");
        }
    }
}

