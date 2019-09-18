package jdbc_2;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库工具类
 * 1.获得连接
 * 2.释放资源
 * 3.设置prestatement参数
 */
public class JdbcUtils {


    //1 自动返回一个数据库连接
    //2 自动释放连接
    // 初次使用时读取配置文件
    private  static Properties props;
    static {

        try{
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("JdbcConfig.properties");
            JdbcUtils.props = new Properties();
            props.load(in); //必须异常处理

            //直接在此处统一注册驱动，这样不用每次在调用时进行注册
            Class.forName(props.getProperty("jdbc.driver"));


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //获取连接
        return DriverManager.getConnection(
                    props.getProperty("jdbc.url"),
                    props.getProperty("jdbc.user"),
                    props.getProperty("jdbc.password")
        );
    }

    public static void close(ResultSet rs,PreparedStatement ps, Connection c){

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
            } finally {
                try{
                    if(c != null) c.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
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
