package com.ch.springfw;

import com.ch.springfw.bean.IUserDao;
import com.ch.springfw.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler=(proxy,method,args)->{
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("1","ch");
            hashMap.put("06","chhhhh");
            hashMap.put("16","cccc");
            return "你被代理了"+method.getName()+":"+hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class},handler);
    }

    @Override
    public Class<IUserDao> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
