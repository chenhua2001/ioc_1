package com.ch.springfw;

public class BeanReference {
    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String beanName() {
        return beanName;
    }

    public BeanReference beanName(String beanName) {
        this.beanName = beanName;
        return this;
    }
}
