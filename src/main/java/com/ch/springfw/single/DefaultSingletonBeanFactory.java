package com.ch.springfw.single;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanFactory implements SingletonBeanFactory{
    private Map<String,Object> singletonMap;

    public DefaultSingletonBeanFactory() {
        singletonMap=new HashMap<>();
    }

    protected void addSingleton(String beanName,Object bean){
        singletonMap.put(beanName,bean);
    }


    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }
}
