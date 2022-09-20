package com.ch.springfw.instantiate;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String name, Object[] args) throws BeanException {
        Class beanClass = beanDefinition.beanClass();
        Constructor declaredConstructor;
        Class[] argTypes;
        if(args!=null&&args.length>0){
            argTypes=new Class[args.length];
            int i=0;
            for (Object arg : args) {
                argTypes[i++]=arg.getClass();
            }
        }else argTypes=null;
        try {
            declaredConstructor= beanClass.getDeclaredConstructor(argTypes);
            return declaredConstructor.newInstance(args);
        } catch (NoSuchMethodException|IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new BeanException("no constructor with such argsType"+ Arrays.toString(argTypes));
        }
    }
}
