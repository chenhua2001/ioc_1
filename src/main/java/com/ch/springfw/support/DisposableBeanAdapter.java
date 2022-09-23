package com.ch.springfw.support;

import cn.hutool.core.util.StrUtil;
import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.processor.DisposableBean;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName=beanDefinition.destroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean)
            ((DisposableBean)bean).destroy();
        if(StrUtil.isNotEmpty(destroyMethodName)&&!(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method method = bean.getClass().getMethod(destroyMethodName);
            if(null==method)
                throw  new BeanException("Couldn't find a destory method named '"+destroyMethodName+"'");
            method.invoke(bean);
        }
    }
}
