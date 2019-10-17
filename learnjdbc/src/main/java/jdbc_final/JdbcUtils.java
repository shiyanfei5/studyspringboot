package jdbc_final;

import jdbc_final.datasource.MyDataSource;

import java.sql.*;


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
    private  static MyDataSource dataSource = new MyDataSource();

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //获取连接
        return dataSource.getConnection();
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
