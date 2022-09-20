package com.ch.springfw;

import java.util.HashMap;

public class BeanFactory {
    private HashMap<String,BeanDefinition> definitionMap;

    public BeanFactory() {
        this.definitionMap = new HashMap<>();
    }

    public Object getBean(String name){
        return definitionMap.get(name).getBean();
    }
    public void registerDefinition(String name,BeanDefinition beanDefinition){
        definitionMap.put(name,beanDefinition);
    }
}
