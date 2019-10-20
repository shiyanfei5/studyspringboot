package aop.dao;


import aop.JdbcThreadUtil;
import jdbc_final.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类，抽象SQL的执行，封装SQL
 * 保证传入SQL即可执行
 */
public  class BaseDao {

    /**
     * 执行增删改，通过ps.executeUpdate方法
     */
    private JdbcThreadUtil jdbc = new JdbcThreadUtil();

    public int executeUpdate(String sql, Object ... args){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = jdbc.getConnection();
            ps = connection.prepareStatement(sql);
            JdbcThreadUtil.setPrepareStatementArgs(ps,args);
            return ps.executeUpdate();
        } catch (SQLException e){
            throw  new DaoException(e.getMessage(),e);  //将编译异常转换为运行时异常
        } finally {
            JdbcThreadUtil.close(rs,ps);
        }
    }


    public <T> List<T> executeQuery(String sql, Class<T> clazz, IRowMapper rowMapper , Object ... args ){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = jdbc.getConnection();
            ps = connection.prepareStatement(sql);
            JdbcThreadUtil.setPrepareStatementArgs(ps,args);
            rs = ps.executeQuery(); //用于接收结果

            List<T> res = new ArrayList<>(); //结果集
            while(rs.next()){
               T item =  clazz.cast( rowMapper.mapRow(rs) );    //类型转换
               res.add(item);
            }
            return res;
        } catch (SQLException e){
            throw  new DaoException(e.getMessage(),e);  //将编译异常转换为运行时异常
        } finally {
            JdbcThreadUtil.close(rs,ps);
        }

    }



}
