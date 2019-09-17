package jdbc;


import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
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

    
}
