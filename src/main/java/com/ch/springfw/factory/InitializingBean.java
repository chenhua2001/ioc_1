package com.ch.springfw.factory;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
