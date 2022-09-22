package com.ch.springfw.resource;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.ch.springfw.BeanDefinition;
import com.ch.springfw.BeanReference;
import com.ch.springfw.PropertyValue;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.rigister.BeanDefinitionRegistry;
import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws BeanException, IOException, ClassNotFoundException {
        doLoadBeanDefinition(resource.getInputStream());
    }

    @Override
    public void loadBeanDefinition(Resource... resources) throws BeanException, IOException, ClassNotFoundException {
        for (Resource resource : resources) {
            doLoadBeanDefinition(resource.getInputStream());
        }
    }

    @Override
    public void loadBeanDefinition(String location) throws BeanException, IOException, ClassNotFoundException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinition(resource);
    }

    protected void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException, BeanException {
        Document document = XmlUtil.readXML(inputStream);
        Element root=document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if(!(childNodes.item(i) instanceof Element)) continue;
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            Element bean =(Element) childNodes.item(i);
            String id=bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            Class<?> clazz = Class.forName(className);
            String beanName=StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition=new BeanDefinition(clazz);
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if(!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if(!("property".equals(bean.getChildNodes().item(j).getNodeName()))) continue;
                Element property=(Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef=property.getAttribute("ref");
                Object value=StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef):attrValue;
                PropertyValue propertyValue=new PropertyValue(attrName,value);
                beanDefinition.propertyValues().addProperty(propertyValue);
            }
            if(getBeanRegistry().containsBeanDefinition(name))
                throw new BeanException("Duplicate beanName["+beanName+"] is not allowed");
            getBeanRegistry().addBeanDefinitionRegistry(beanName,beanDefinition);
        }
    }

}
