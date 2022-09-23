package com.ch.springfw.factory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);
    Object applyBeanPostProcessorsAfterInitialization(Object existBean,String beanName);
}
