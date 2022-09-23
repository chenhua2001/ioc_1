package com.ch.springfw.factory.aware;

import com.ch.springfw.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext context);
}
