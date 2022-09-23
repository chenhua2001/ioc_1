package com.ch.springfw.process;

import com.ch.springfw.bean.UserDao;
import com.ch.springfw.processor.BeanPostProcessor;

public class MyPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if("userDao".equals(beanName)){
            UserDao userDao=(UserDao) bean;
            userDao.name+="and <huahua>";
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
