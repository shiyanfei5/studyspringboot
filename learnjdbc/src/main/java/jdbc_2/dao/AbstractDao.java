package jdbc_2.dao;


import jdbc_2.JdbcUtils;
import jdbc_2.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 抽象类，抽象SQL的执行，封装SQL
 * 保证传入SQL即可执行
 */
public abstract class AbstractDao {

    /**
     * 执行增删改，通过ps.executeUpdate方法
     */
    public int executeUpdate(String sql, Object ... args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            JdbcUtils.setPrepareStatementArgs(ps,args);
            return ps.executeUpdate();
        } catch (SQLException e){
            throw  new DaoException(e.getMessage(),e);  //将编译异常转换为运行时异常
        } finally {
            JdbcUtils.close(rs,ps,connection);
        }
    }


    public Object execute(String sql, Object ... args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            JdbcUtils.setPrepareStatementArgs(ps,args);

            rs = ps.executeQuery(); //用于接收结果



        } catch (SQLException e){
            throw  new DaoException(e.getMessage(),e);  //将编译异常转换为运行时异常
        } finally {
            JdbcUtils.close(rs,ps,connection);
        }

    }

    public abstract rowMapper();   //模板设计模式


}
