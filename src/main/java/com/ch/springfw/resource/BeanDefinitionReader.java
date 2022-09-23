package com.ch.springfw.resource;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.rigister.BeanDefinitionRegistry;

import java.io.IOException;

public interface BeanDefinitionReader {
    BeanDefinitionRegistry getBeanRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinition(Resource resource) throws BeanException, IOException, ClassNotFoundException;
    void loadBeanDefinition(Resource... resources) throws BeanException, IOException, ClassNotFoundException;
    void loadBeanDefinition(String location) throws BeanException, IOException, ClassNotFoundException;
    void loadBeanDefinition(String... location) throws BeanException, IOException, ClassNotFoundException;

}
