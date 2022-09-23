package com.ch.springfw.factory;

import com.ch.springfw.expection.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{
    /*
    * 按照类型返回Bean
    * */
    <T> Map<String,T> getBeanOfType(Class<T> type) throws BeanException;
    /*
    * 返回所有Bean名称
    * */
    String[] getBeanDefinitionNames();
}
