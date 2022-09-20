package com.ch.springfw;


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
    /*无参获取bean
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
    
}
