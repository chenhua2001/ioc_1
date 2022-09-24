package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.single.DefaultSingletonBeanFactory;
import com.ch.springfw.factory.support.FactoryBeanRegistrySupport;
import com.ch.springfw.processor.BeanPostProcessor;
import com.ch.springfw.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableFactory{
    /*
    * BeanPostProcessor to apply in createBean
    * */
    private final List<BeanPostProcessor> beanPostProcessors=new ArrayList<>();

    private final ClassLoader classLoader= ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String name,Object[] args) throws BeanException {
        return doGetBean(name,args);
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return doGetBean(name,null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return (T) getBean(name);
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

    protected ClassLoader getClassLoader(){
        return classLoader;
    }

    protected <T> T doGetBean(final String name,final Object[] args) throws BeanException {
        Object shareInstance=getSingleton(name);
        if(shareInstance!=null) return (T)getObjectForBeanInstance(shareInstance,name);
        BeanDefinition beanDefinition=getBeanDefinition(name);
        Object bean=createBean(name,beanDefinition,args);
        return (T) getObjectForBeanInstance(bean,name);
    }

    private Object getObjectForBeanInstance(Object beanInstance,String name) throws BeanException {
        if(!(beanInstance instanceof FactoryBean))
            return beanInstance;
        Object object=getCachedObjectForFactoryBean(name);
        if(object==null){
            FactoryBean<?> factoryBean=(FactoryBean<?>)beanInstance;
            object=getCachedObjectForFactoryBean(name,factoryBean);
        }
        return object;
    }
}
