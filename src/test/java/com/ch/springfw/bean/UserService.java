package com.ch.springfw.bean;

public class UserService {

    public UserDao userDao;

    String name="default name";

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
}

