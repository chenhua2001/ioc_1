package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.instantiate.InstantiationStrategy;
import com.ch.springfw.instantiate.SimpleInstantiationStrategy;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    InstantiationStrategy instantiationStrategy=new SimpleInstantiationStrategy();

    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeanException {
         Class beanClass = beanDefinition.getBeanClass();
         Object b=null;
         try {
             b = instantiationStrategy.instantiate(beanDefinition,beanName,args);
         } catch (Exception e) {
            throw new BeanException("bean instance failed！",e);
         }
         addSingleton(beanName,b);
         return b;
    }
}
