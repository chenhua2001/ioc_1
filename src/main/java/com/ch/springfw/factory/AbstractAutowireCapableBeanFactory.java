package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.BeanReference;
import com.ch.springfw.PropertyValue;
import com.ch.springfw.PropertyValues;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.instantiate.InstantiationStrategy;
import com.ch.springfw.instantiate.SimpleInstantiationStrategy;
import com.ch.springfw.util.BeanUtil;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    InstantiationStrategy instantiationStrategy=new SimpleInstantiationStrategy();

    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeanException {
         Class beanClass = beanDefinition.beanClass();
         Object b=null;
         try {
             b = instantiationStrategy.instantiate(beanDefinition,beanName,args);
             //属性
             addProperties(b,beanDefinition,beanName);
         } catch (Exception e) {
            throw new BeanException("bean instance failed！",e);
         }
         addSingleton(beanName,b);
         return b;
    }

    protected void addProperties(Object b, BeanDefinition beanDefinition, String beanName) throws BeanException {
        PropertyValues propertyValues = beanDefinition.propertyValues();
        PropertyValue[] properValues = propertyValues.getProperValues();
        for (PropertyValue properValue : properValues) {
            String name = properValue.name();
            Object value = properValue.value();
            if(value instanceof BeanReference){
                BeanReference beanReference=(BeanReference) value;
                value=getBean(beanReference.beanName());
            }
            try {
                BeanUtil.setBeanProperty(b,name,value);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new BeanException("set property "+name+" fail in bean "+beanName);
            }
        }
    }
}
