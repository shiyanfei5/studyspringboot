package jdbc_final.dao.impl;

import jdbc_final.dao.IUserDao;

import jdbc_final.entity.User;
import jdbc_final.datasource.MyDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;



public class UserDaoImplTest {

    @Test
    public void test(){
        // 创建连接池对象
        MyDataSource datasource = new MyDataSource();

        // 用来存储待关闭的连接
        List<Connection> connectionsToBeClosed =  new ArrayList<Connection>();

        try {
            // 循环多次从连接池取出连接
            for (int i = 0; i < 11; i++) {
                System.out.println();
                System.out.println("------第" + (i + 1) + "次-------");

                Connection conn = datasource.getConnection();
                System.out.println("使用Connection：" + conn);
                // 1.创建sql模板
                String sql = "select * from user where age = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                // 2.设置模板参数
                preparedStatement.setInt(1, 28);

                // 3.执行语句
                ResultSet rs = preparedStatement.executeQuery();

                // 4.处理结果
                while (rs.next()) {
                    System.out.println(rs.getObject(1) + "\t" + rs.getObject(2) + "\t"
                            + rs.getObject(3) + "\t" + rs.getObject(4));
                }

                // 5.收集连接
                connectionsToBeClosed.add(conn);
            }

            /*
             *  集中释放连接，会产生一个现象：
             *  程序执行到这里，连接池中已有若干个连接
             *  但还没到maxIdleCount，所以此时conn.close是归还池中
             *
             *  从第7次起，conn.close就会真的关闭Connection，因为连接池满了
             * */
            for (int i = 0; i < 11; i++) {
                connectionsToBeClosed.get(i).close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        User user = new User("yfshi",29, new Date(1990-1900,01,30));

        IUserDao userDao = new UserDaoImpl();

        userDao.addUser(user);
        System.out.println(userDao.findUserByName("rju"));
        System.out.println(userDao.getUser(4));

    }


}