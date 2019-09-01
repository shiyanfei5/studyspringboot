package com.yfshi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
public class Student {

    private long id;    //学号

    private String name ; //姓名

    private int age; //年龄

    private List<String> test;


}
