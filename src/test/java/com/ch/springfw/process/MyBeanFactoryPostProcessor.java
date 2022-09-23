package com.ch.springfw.process;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.PropertyValue;
import com.ch.springfw.PropertyValues;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.ConfigurableListableBeanFactory;
import com.ch.springfw.processor.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition userDaoDefinition = beanFactory.getBeanDefinition("userDao");
        PropertyValues propertyValues = userDaoDefinition.propertyValues();
        propertyValues.addProperty(new PropertyValue("name","<chenhua>"));
    }

}
