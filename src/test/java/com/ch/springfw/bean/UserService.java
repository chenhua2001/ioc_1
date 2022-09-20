package com.ch.springfw.bean;

public class UserService {
    String name="default name";

    public UserService(String name) {
        this.name = name;
    }

    public void getUser(){
        System.out.println("get User {chenhua}"+name);
    }
}

