package com.ch.springfw;

public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;
    private String initMethodName;
    private String destroyMethodName;

    public BeanDefinition(Class _beanClass){
        this(_beanClass,null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues==null?new PropertyValues()
        :propertyValues;
    }
    public BeanDefinition beanClass(Class beanClass) {
        this.beanClass=beanClass;
        return this;
    }

    public Class beanClass(){
        return beanClass;
    }

    public PropertyValues propertyValues() {
        return propertyValues;
    }

    public BeanDefinition propertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
        return this;
    }


}
