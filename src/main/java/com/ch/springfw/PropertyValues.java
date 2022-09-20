package com.ch.springfw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PropertyValues {
    private List<PropertyValue> propertyValues;

    public PropertyValues() {
        propertyValues=new ArrayList<>();
    }

    public void addProperty(PropertyValue propertyValue){
        propertyValues.add(propertyValue);
    }

    public PropertyValue[] getProperValues(){
        return propertyValues.toArray(new PropertyValue[0]);
    }

    public PropertyValue getProperValue(String name){
        for (PropertyValue propertyValue : propertyValues) {
            if(name.equals(propertyValue.name())){
                return propertyValue;
            }
        }
        return null;
    }


}
