package aop;

import jdbc_final.datasource.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcThreadUtil {

    //1 自动返回一个数据库连接
    //2 自动释放连接
    // 初次使用时读取配置文件
    private  static MyDataSource dataSource = new MyDataSource();


    //static， 属于类的属性内存就一份，不同实例共享
    //
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 获取数据库连续。若该线程已有则拿，没有则重新从连接池获取
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        //获取连接
        Connection c = threadLocal.get();
        if(c == null){
            //
            c = dataSource.getConnection();
            threadLocal.set(c);
        }
        return c;
    }

    public void removeThreadConnection(){
        try{
            System.out.println("释放了资源");
            threadLocal.get().close();
        } catch (Exception e){
            e.printStackTrace();
        }

        threadLocal.remove();
    }


    public static void close(ResultSet rs, PreparedStatement ps){

        //必须这么写，保证一个抛出来异常后后面的继续执行
        try{
            if(rs != null) rs.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    /**
     * 设置PrepareStatement的参数
     * @param ps
     * @param args
     * @throws SQLException
     */
    public static void setPrepareStatementArgs
    (PreparedStatement ps,  Object ... args) throws SQLException{
        for(int i = 0 ; i < args.length; i ++){
            ps.setObject(i+1,args[i]);
        }
    }
}
