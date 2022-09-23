package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.processor.BeanPostProcessor;
import com.ch.springfw.rigister.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

public  class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry,ConfigurableListableBeanFactory {
    private HashMap<String,BeanDefinition> definitionHashMap;

    public DefaultListableBeanFactory() {
        definitionHashMap=new HashMap<>();
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = definitionHashMap.get(beanName);
        if(beanDefinition==null) throw new BeanException("no bean named "+beanName+" was defined!");
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeanException {

    }


    @Override
    public void addBeanDefinitionRegistry(String name, BeanDefinition beanDefinition) {
        definitionHashMap.put(name,beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return definitionHashMap.containsKey(name);
    }


    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeanException{
        Map<String,T> result=new HashMap<>();
        definitionHashMap.forEach((name,definition)->{
            if(type.isAssignableFrom(definition.beanClass())) {
                try {
                    result.put(name,(T)getBean(name));
                } catch (BeanException e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }
}
