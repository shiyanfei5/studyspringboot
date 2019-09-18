package jdbc_1;


import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class driverDemo {


    @Test
    public void testDriver() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://132.232.54.199:3306/studyjpa?characterEncoding=UTF-8";
        Properties info = new Properties();
        info.put("user","root");
        info.put("password","123456");


        Connection conn = driver.connect(url,info);
        System.out.println(conn);




    }


    @Test
    public void testDriver2() throws SQLException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //注册驱动
            String url = "jdbc:mysql://132.232.54.199:3306/studyjpa?characterEncoding=UTF-8";

            Connection c = DriverManager.getConnection(url,"root","123456");
            //获取连接

        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
