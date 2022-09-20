package com.ch.springfw;

public class BeanDefinition {
    private Object bean;
    public BeanDefinition(Object _bean){
        bean=_bean;
    }
    public Object getBean(){
        return bean;
    }
}
