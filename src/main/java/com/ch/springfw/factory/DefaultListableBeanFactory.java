package com.ch.springfw.factory;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.rigister.BeanDefinitionRegistry;

import java.util.HashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    private HashMap<String,BeanDefinition> definitionHashMap;

    public DefaultListableBeanFactory() {
        definitionHashMap=new HashMap<>();
    }

    @Override
    BeanDefinition getBeanDefinition(String beanName) throws BeanException {
        BeanDefinition beanDefinition = definitionHashMap.get(beanName);
        if(beanDefinition==null) throw new BeanException("no bean named"+beanName+"was defined!");
        return beanDefinition;
    }

    @Override
    public void addBeanDefinitionRegistry(String name, BeanDefinition beanDefinition) {
        definitionHashMap.put(name,beanDefinition);
    }
}
