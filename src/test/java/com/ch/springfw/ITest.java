package com.ch.springfw;

import com.ch.springfw.BeanDefinition;
import com.ch.springfw.BeanFactory;
import com.ch.springfw.bean.UserService;
import org.junit.Test;

public class ITest {
    @Test
    public void testGetBeanObject() {
        final BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerDefinition("service", new BeanDefinition(new UserService()));
        UserService service = (UserService) beanFactory.getBean("service");
        service.getUser();

    }
}
