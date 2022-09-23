package com.ch.springfw.factory.single;

import com.ch.springfw.expection.BeanException;
import com.ch.springfw.processor.DisposableBean;
import com.ch.springfw.support.DisposableBeanAdapter;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanFactory implements SingletonBeanFactory{

    private Map<String,Object> singletonMap;
    private Map<String, DisposableBean> disposableBeanMap;

    public DefaultSingletonBeanFactory() {
        singletonMap=new HashMap<>();
        disposableBeanMap=new HashMap<>();
    }

    protected void addSingleton(String beanName,Object bean){
        singletonMap.put(beanName,bean);
    }


    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    @Override
    public void destroySingleton() throws BeanException {
        Object[] names = disposableBeanMap.keySet().toArray();
        //从后往前进行disposable
        for (int i = names.length-1; i>=0; i--) {
            DisposableBean disposableBean = disposableBeanMap.remove(names[i]);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
               throw new BeanException("destroy bean '"+names[i]+"' fail");
            }
        }
    }
    public void registerDisposableBean(String beanName, DisposableBeanAdapter disposableBeanAdapter) {
        disposableBeanMap.put(beanName,disposableBeanAdapter);
    }

}
