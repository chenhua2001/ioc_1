package com.ch.springfw;


import com.ch.springfw.bean.UserDao;
import com.ch.springfw.bean.UserService;
import com.ch.springfw.context.ClassPathXmlApplicationContext;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.BeanFactory;
import com.ch.springfw.factory.DefaultListableBeanFactory;
import com.ch.springfw.process.MyBeanFactoryPostProcessor;
import com.ch.springfw.process.MyPostProcessor;
import com.ch.springfw.resource.DefaultResourceLoader;
import com.ch.springfw.resource.XmlBeanDefinitionReader;
import org.junit.Test;

import java.io.IOException;

public class ITest {
    /*
    * 版本1测试
    * */
    /*@Test
    public void testGetBeanObject() {
        final BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerDefinition("service", new BeanDefinition(new UserService()));
        UserService service = (UserService) beanFactory.getBean("service");
        service.getUser();

    }*/
    /*无参获取bean
    * from:版本3
    */
    @Test
    public void testGetBeanObject() {
        //beanFactory初始化
        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加beanDefinition
        beanFactory.addBeanDefinitionRegistry("userService",new BeanDefinition(UserService.class));
        UserService service;
        try{
            service = (UserService) beanFactory.getBean("userService");
            service.getUser();
        }catch (BeanException e){
            e.printStackTrace();
        }

    }
    /*带参获取bean
     * from:版本3
     */
    @Test
    public void testGetBeanObjectWithArgs() {
        //beanFactory初始化
        final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加beanDefinition
        beanFactory.addBeanDefinitionRegistry("userService",new BeanDefinition(UserService.class));
        UserService service;
        try{
            service = (UserService) beanFactory.getBean("userService",new Object[]{"chenhhhh"});
            service.getUser();
        }catch (BeanException e){
            e.printStackTrace();
        }

    }

    /*
    * 测试自动装配UserDao
    * */
    @Test
    public void testAutoWireBean() throws BeanException {
        DefaultListableBeanFactory defaultListableBeanFactory=new DefaultListableBeanFactory();
        //开始
        PropertyValues propertyValues=new PropertyValues();
        propertyValues.addProperty(new PropertyValue("name","chenhua"));
        defaultListableBeanFactory.addBeanDefinitionRegistry("userDao",new BeanDefinition(UserDao.class,propertyValues));
        PropertyValues propertyValues1=new PropertyValues();
        propertyValues1.addProperty(new PropertyValue("userDao",new BeanReference("userDao")));
        defaultListableBeanFactory.addBeanDefinitionRegistry("userService",new BeanDefinition(UserService.class,propertyValues1));
        //定义资源结束
        UserService userService = (UserService)defaultListableBeanFactory.getBean("userService");
        userService.getUserByDao();
    }
    @Test
    public void testLoadResource() throws BeanException, IOException, ClassNotFoundException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinition("classpath:spring.xml");
        UserService userService=(UserService) defaultListableBeanFactory.getBean("userService");
        userService.getUserByDao();
    }
    /*
    * 不使用应用上下文
    * */
    @Test
    public void test_process_without_context() throws ClassNotFoundException, IOException, BeanException {
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        //加载资源
        reader.loadBeanDefinition("classpath:spring.xml");
        //加载资源后使用工程类处理器修改类定义
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(factory);
        //实例化之后修改对象属性
        MyPostProcessor myPostProcessor = new MyPostProcessor();
        factory.addBeanPostProcessor(myPostProcessor);
        UserService userService =(UserService) factory.getBean("userService");
        userService.getUserByDao();
    }
    @Test
    public void test_process_with_context() throws BeanException, IOException, ClassNotFoundException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring2.xml");
        UserService userService = (UserService)classPathXmlApplicationContext.getBean("userService");
        userService.getUserByDao();
    }

    @Test
    public void testInitDestroy() throws ClassNotFoundException, IOException, BeanException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring3.xml");
        UserService userService = (UserService)classPathXmlApplicationContext.getBean("userService");
        userService.getUserByDao();
        classPathXmlApplicationContext.close();
    }

}
