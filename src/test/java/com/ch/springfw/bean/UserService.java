package com.ch.springfw.bean;

import com.ch.springfw.context.AbstractApplicationContext;
import com.ch.springfw.context.ApplicationContext;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.BeanFactory;
import com.ch.springfw.factory.aware.ApplicationContextAware;
import com.ch.springfw.factory.aware.BeanClassLoaderAware;
import com.ch.springfw.factory.aware.BeanFactoryAware;
import com.ch.springfw.factory.aware.BeanNameAware;
import com.ch.springfw.processor.DisposableBean;
import com.ch.springfw.processor.InitializingBean;

public class UserService implements InitializingBean, DisposableBean, ApplicationContextAware, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware {

    public UserDao userDao;

    public String name="default name";
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private ClassLoader classLoader;

    public UserService(String name) {
        this.name = name;
    }

    public UserService() {
    }

    public void getUser(){
        System.out.println("get User {chenhua}"+name);
    }

    public void getUserByDao(){
        userDao.getUser();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void sayHello(){
        System.out.println("init-method"+" hello");
    }
    public void sayBye(){
        System.out.println("bye~");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after Properties Set");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext=context;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader=classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory= beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("the name of the bean is >"+beanName);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", applicationContext=" + applicationContext +
                ", beanFactory=" + beanFactory +
                ", classLoader=" + classLoader +
                '}';
    }
}

