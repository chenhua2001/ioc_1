package com.ch.springfw;

public class BeanDefinition {
    private Class beanClass;
    public BeanDefinition(Class _beanClass){
        beanClass=_beanClass;
    }
    public Class getBeanClass(){
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
