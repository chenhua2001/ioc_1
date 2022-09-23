package com.ch.springfw.processor;

public interface DisposableBean {
    void destroy() throws Exception;
}
