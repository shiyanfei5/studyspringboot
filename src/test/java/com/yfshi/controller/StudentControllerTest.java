package com.yfshi.controller;

import com.yfshi.Application;
import com.yfshi.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class StudentControllerTest {

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        StudentController tester = context.getBean("studentController",StudentController.class);
        Student a  = new Student();
        a.setAge(12);
        a.setName("yfshi");
        a.setId(1220122);
        ArrayList<String> li = new ArrayList<String>();
        li.add("asd");
        li.add("sdd");
        a.setTest(li);
        tester.save(a);
        tester.findall();
    }
}