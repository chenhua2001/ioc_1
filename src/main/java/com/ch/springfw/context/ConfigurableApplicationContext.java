package com.ch.springfw.context;

import com.ch.springfw.expection.BeanException;

import java.io.IOException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    void refresh() throws BeanException, IOException, ClassNotFoundException;
}
