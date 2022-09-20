package com.ch.springfw;

public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public PropertyValue name(String name) {
        this.name = name;
        return this;
    }

    public Object value() {
        return value;
    }

    public PropertyValue value(Object value) {
        this.value = value;
        return this;
    }
}
