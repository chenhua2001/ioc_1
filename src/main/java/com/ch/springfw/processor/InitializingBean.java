package com.ch.springfw.processor;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
