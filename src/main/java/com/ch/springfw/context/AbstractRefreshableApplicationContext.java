package com.ch.springfw.context;

import com.ch.springfw.context.AbstractApplicationContext;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.ConfigurableListableBeanFactory;
import com.ch.springfw.factory.DefaultListableBeanFactory;

import java.io.IOException;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private DefaultListableBeanFactory beanFactory;
    @Override
    protected void refreshBeanFactory() throws BeanException, IOException, ClassNotFoundException {
        beanFactory=new DefaultListableBeanFactory();
        loadBeanDefinition(beanFactory);
    }
    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory) throws ClassNotFoundException, IOException, BeanException;

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
