package main.com.syf.studyannotation.myjpa.entity;


import main.com.syf.studyannotation.myjpa.jpaannotation.Colomn;
import main.com.syf.studyannotation.myjpa.jpaannotation.Table;

import java.util.Date;


@Table("user")
public class User {

    private Integer id;
    @Colomn("name")
    private String name;
    @Colomn("age")
    private Integer age;
    @Colomn("birthday")
    private Date birthday;

    private String test;

    public User(String name, Integer age, Date birthday, String test) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.test = test;
    }
}
