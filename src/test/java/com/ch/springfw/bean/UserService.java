package com.ch.springfw.bean;

import com.ch.springfw.processor.DisposableBean;
import com.ch.springfw.processor.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {

    public UserDao userDao;

    public String name="default name";

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
}

