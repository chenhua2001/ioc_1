package com.ch.springfw.context;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.ConfigurableListableBeanFactory;
import com.ch.springfw.processor.ApplicationContextAwareProcessor;
import com.ch.springfw.processor.BeanFactoryPostProcessor;
import com.ch.springfw.processor.BeanPostProcessor;
import com.ch.springfw.resource.DefaultResourceLoader;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeanException, IOException, ClassNotFoundException {
        //1.创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //在这里将ApplicationContext放到factory
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //3.在Bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        //4.注册BeanPostProcessor
        registerBeanPostProcessor(beanFactory);
        //实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }
    protected abstract void refreshBeanFactory() throws BeanException, IOException, ClassNotFoundException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap=beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessorMap.values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
    }
    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        Map<String, BeanPostProcessor> postProcessor = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor processor : postProcessor.values()) {
            beanFactory.addBeanPostProcessor(processor);
        }
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object[] args) throws BeanException {
        return getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public void registerShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {

        try {
            getBeanFactory().destroySingleton();
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }
}
