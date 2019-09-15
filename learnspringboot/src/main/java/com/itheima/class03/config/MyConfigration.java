package com.itheima.class03.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.itheima.class03"} )
public class MyConfigration {



    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://132.232.54.199:3306/studyjpa?characterEncoding=UTF-8");
            ds.setUser("root");
            ds.setPassword("123456");
        } catch (Exception e){
            e.printStackTrace();
        }
        return ds;
    }

    @Bean
    @Scope("prototype") // 需要注意必须使用多例、
    public QueryRunner queryRunner(){
        return new QueryRunner(dataSource());
    }

}
