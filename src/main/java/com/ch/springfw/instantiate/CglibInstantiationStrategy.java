package com.ch.springfw.instantiate;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.util.ClassesTypeUtil;
import net.sf.cglib.proxy.Enhancer;

public class CglibInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String name, Object[] args) throws BeanException {
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(beanDefinition.beanClass());
        if(args==null||args.length==0)
            return enhancer.create();
        else return enhancer.create(ClassesTypeUtil.getClassesType(args),args);
    }
}
