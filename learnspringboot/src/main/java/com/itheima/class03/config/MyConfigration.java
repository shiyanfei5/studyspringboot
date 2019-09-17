package com.itheima.class03.config;


import com.itheima.class03.bean.TestBean1;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;


@ComponentScan(basePackages = {"com.itheima.class03"} )
@PropertySource("classpath:itheima_class03_jdbcConfig.properties")
public class MyConfigration {

    @Bean
    public TestBean1 testBean1(){
        System.out.println("生成一个TestBean");
        return new TestBean1();
    }

}
