package com.ch.springfw.factory;

import cn.hutool.core.util.StrUtil;
import com.ch.springfw.BeanDefinition;
import com.ch.springfw.BeanReference;
import com.ch.springfw.PropertyValue;
import com.ch.springfw.PropertyValues;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.aware.Aware;
import com.ch.springfw.factory.aware.BeanClassLoaderAware;
import com.ch.springfw.factory.aware.BeanFactoryAware;
import com.ch.springfw.factory.aware.BeanNameAware;
import com.ch.springfw.instantiate.InstantiationStrategy;
import com.ch.springfw.instantiate.SimpleInstantiationStrategy;
import com.ch.springfw.processor.BeanPostProcessor;
import com.ch.springfw.processor.DisposableBean;
import com.ch.springfw.processor.InitializingBean;
import com.ch.springfw.support.DisposableBeanAdapter;
import com.ch.springfw.util.BeanUtil;

import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    InstantiationStrategy instantiationStrategy=new SimpleInstantiationStrategy();

    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeanException {
         Class beanClass = beanDefinition.beanClass();
         Object b=null;
         try {
             b = instantiationStrategy.instantiate(beanDefinition,beanName,args);
             //属性
             addProperties(b,beanDefinition,beanName);
             //执行bean的初始化方法和BeanPostProcessor的前置和后置处理
             b=initializeBean(beanName,b,beanDefinition);
         } catch (Exception e) {
            throw new BeanException("bean instance failed！",e);
         }
         registerDisposableBeanIfNecessary(beanName,b,beanDefinition);
         if(beanDefinition.isSingle())
            addSingleton(beanName,b);
         return b;
    }

    private Object initializeBean(String beanName, Object b, BeanDefinition beanDefinition) throws Exception {
        //invokeAwareMethods：beanFactory，BeanClassLoader，BeanName
        if(b instanceof Aware) {
            if (b instanceof BeanFactoryAware) {
                ((BeanFactoryAware) b).setBeanFactory(this);
            }
            if(b instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware)b).setBeanClassLoader(getClassLoader());
            }
            if(b instanceof BeanNameAware)
                ((BeanNameAware)b).setBeanName(beanName);
        }
        //ApplicationContext会在这边由PostProcessor处理
        Object wrappedBean=applyBeanPostProcessorsBeforeInitialization(b,beanName);
        invokeInitMethod(wrappedBean,beanName,beanDefinition);
        wrappedBean=applyBeanPostProcessorsAfterInitialization(wrappedBean,beanName);
        return wrappedBean;
    }

    private void invokeInitMethod(Object bean, String beanName, BeanDefinition beanDefinition) throws Exception {
        if(bean instanceof InitializingBean)
            ((InitializingBean)bean).afterPropertiesSet();
        String initMethodName = beanDefinition.initMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method method = beanDefinition.beanClass().getMethod(initMethodName);
            if(null==initMethodName) throw new BeanException("can not find an init method named '"+initMethodName+"'");
            method.invoke(bean);
        }
    }
    
    protected void  registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        if(!beanDefinition.isSingle()) return;
        if(bean instanceof DisposableBean ||StrUtil.isNotEmpty(beanDefinition.destroyMethodName()))
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
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
                throw new BeanException("set property ["+name+"] fail in bean "+beanName);
            }
        }
    }
    public InstantiationStrategy getInstantiationStrategy(){
        return instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object last=existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessor()) {
            existingBean=processor.postProcessBeforeInitialization(existingBean,beanName);
            if(existingBean==null) return last;
            last=existingBean;
        }
        return existingBean;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existBean, String beanName) {
        Object last=existBean;
        for (BeanPostProcessor processor : getBeanPostProcessor()) {
            existBean= processor.postProcessAfterInitialization(existBean,beanName);
            if(existBean==null) return last;
            last=existBean;
        }
        return existBean;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy=instantiationStrategy;
    }

}
