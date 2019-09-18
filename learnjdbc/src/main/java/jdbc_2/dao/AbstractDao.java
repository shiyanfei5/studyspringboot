package jdbc_2.dao;


import jdbc_2.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 抽象SQL的执行，封装SQL，传入SQL即可执行
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
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs,ps,connection);
        }

    }





}
