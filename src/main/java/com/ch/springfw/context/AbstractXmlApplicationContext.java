package com.ch.springfw.context;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.DefaultListableBeanFactory;
import com.ch.springfw.resource.XmlBeanDefinitionReader;

import java.io.IOException;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) throws ClassNotFoundException, IOException, BeanException {
        XmlBeanDefinitionReader beanDefinitionReader=new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations= getConfigLocations();
        if(null!=configLocations){
            beanDefinitionReader.loadBeanDefinition(configLocations);
        }

    }
    protected abstract String[] getConfigLocations();
}
