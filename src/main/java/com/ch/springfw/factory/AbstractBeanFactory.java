package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.single.DefaultSingletonBeanFactory;
import com.ch.springfw.processor.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanFactory implements ConfigurableFactory{
    /*
    * BeanPostProcessor to apply in createBean
    * */
    private final List<BeanPostProcessor> beanPostProcessors=new ArrayList<>();

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

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessor() {
        return beanPostProcessors;
    }
}
