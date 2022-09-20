package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
         Class beanClass = beanDefinition.getBeanClass();
         Object b=null;
         try {
             b = beanClass.newInstance();//todo: 有参数的实例化方式
         } catch (InstantiationException |IllegalAccessException e) {
            throw new BeanException("bean instance failed！");
         }
         addSingleton(beanName,b);
         return b;
    }
}
