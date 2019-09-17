package com.itheima.class03.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;


/**
 * 数据库相关配置类
 */
@Configuration
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(this.driver);
            ds.setJdbcUrl(this.url);
            ds.setUser(this.username);
            ds.setPassword(this.password);
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
