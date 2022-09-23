package com.ch.springfw;

import cn.hutool.core.io.IoUtil;
import com.ch.springfw.resource.DefaultResourceLoader;
import com.ch.springfw.resource.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceTest {
    private DefaultResourceLoader resourceLoader;
    @Before
    public void init(){
        resourceLoader=new DefaultResourceLoader();
    }
    @Test
    public void testClassLoader() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.read(inputStream));
    }
    @Test
    public void testFile() throws IOException {
        Resource resource=resourceLoader.getResource("src/main/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.read(inputStream));
    }
    @Test
    public void testHttp() throws IOException {
        Resource resource=resourceLoader.getResource("src/main/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        System.out.println(IoUtil.read(inputStream));
    }
}
