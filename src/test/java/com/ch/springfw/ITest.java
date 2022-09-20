package com.ch.springfw;


import com.ch.springfw.bean.UserDao;
import com.ch.springfw.bean.UserService;
import com.ch.springfw.expection.BeanException;
import com.ch.springfw.factory.BeanFactory;
import com.ch.springfw.factory.DefaultListableBeanFactory;
import org.junit.Test;

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
        PropertyValues propertyValues=new PropertyValues();
        propertyValues.addProperty(new PropertyValue("name","chenhua"));
        defaultListableBeanFactory.addBeanDefinitionRegistry("userDao",new BeanDefinition(UserDao.class,propertyValues));
        PropertyValues propertyValues1=new PropertyValues();
        propertyValues1.addProperty(new PropertyValue("userDao",new BeanReference("userDao")));
        defaultListableBeanFactory.addBeanDefinitionRegistry("userService",new BeanDefinition(UserService.class,propertyValues1));
        UserService userService = (UserService)defaultListableBeanFactory.getBean("userService");
        userService.getUserByDao();
    }
}
