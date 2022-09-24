package com.ch.springfw.util;

import java.lang.reflect.Field;

public class BeanUtil {
    public static void setBeanProperty(Object bean,String name,Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = bean.getClass();
        Field declaredField = aClass.getDeclaredField(name);
        declaredField.setAccessible(true);
        declaredField.set(bean,value);
    }

}
