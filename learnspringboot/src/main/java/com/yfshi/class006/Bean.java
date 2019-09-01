package com.yfshi.class006;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;


public class Bean {
    private AnotherBean anotherBean;
    private String string;
    //set注入部分
    private AnotherBean anotherBean1;
    private String string1;
    //List
    private List<String> stringList;
    private List<AnotherBean> anotherBeanList;
    //List
    private Map<String,String> stringMap;
    private Map<AnotherBean,AnotherBean> anotherBeanMap;

    public Bean(AnotherBean anotherBean, String string) {
        System.out.println("构造方法进行注入");
        this.anotherBean = anotherBean;
        this.string = string;
    }
    public void setAnotherBean1(AnotherBean anotherBean1) {
        System.out.println("普通set进行引用注入---AnotherBean1");
        this.anotherBean1 = anotherBean1;
    }
    public void setString1(String string1) {
        System.out.println("普通string进行值注入---string1");
        this.string1 = string1;
    }
    public void setStringList(List<String> stringList) {
        System.out.println("普通List进行引用注入---stringList");
        this.stringList = stringList;
    }
    public void setAnotherBeanList(List<AnotherBean> anotherBeanList) {
        System.out.println("普通List进行引用注入---anotherBeanList");
        this.anotherBeanList = anotherBeanList;
    }
    public void setStringMap(Map<String, String> stringMap) {
        System.out.println("普通Map进行值注入---stringMap");
        this.stringMap = stringMap;
    }
    public void setAnotherBeanMap(Map<AnotherBean, AnotherBean> anotherBeanMap) {
        System.out.println("普通Map进行引用注入---anotherBeanMap");
        this.anotherBeanMap = anotherBeanMap;
    }
}
