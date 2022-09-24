package com.ch.springfw;

import com.ch.springfw.factory.ConfigurableFactory;

public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableFactory.SCOPE_SINGLETON;
    String SCOPE_RPOTOTYPE = ConfigurableFactory.SCOPE_PROTOTYPE;

    private Class beanClass;
    private PropertyValues propertyValues;
    private String initMethodName;
    private String destroyMethodName;

    private String scope=SCOPE_RPOTOTYPE;

    private boolean single=true;

    private boolean prototype=false;



    public BeanDefinition(Class _beanClass){
        this(_beanClass,null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues==null?new PropertyValues()
        :propertyValues;
    }

    public void setScope(String scope){
        this.scope=scope;
        this.single=SCOPE_SINGLETON.equals(scope);
        this.prototype=SCOPE_RPOTOTYPE.equals(scope);
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

    public String initMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String destroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public boolean isSingle(){
        return single;
    }
}
