package com.ch.springfw.util;

import java.util.Arrays;

public class ClassesTypeUtil {
    public static Class[] getClassesType(Object[] objects){
        Class[] classes =(Class[]) Arrays.stream(objects).map(Object::getClass).toArray();
        return classes;
    }
}
