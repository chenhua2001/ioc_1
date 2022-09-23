package com.ch.springfw.context;

import com.ch.springfw.expection.BeanException;

import java.io.IOException;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private String[] configLocation;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String[] configLocation) throws BeanException, IOException, ClassNotFoundException {
        this.configLocation = configLocation;
        refresh();
    }

    public ClassPathXmlApplicationContext(String configLocation) throws BeanException, IOException, ClassNotFoundException {
        this(new String[]{configLocation});
    }


    @Override
    protected String[] getConfigLocations() {
        return configLocation;
    }
}
