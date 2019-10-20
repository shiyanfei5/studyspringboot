package aop.transation;


import aop.JdbcThreadUtil;

import java.sql.Connection;

public class TransationManager {


    private JdbcThreadUtil jdbcThreadUtil = new JdbcThreadUtil();

    public void beginTransation(){
        try{
            System.out.println("~~~~事务已经开始");
            Connection c = jdbcThreadUtil.getConnection();
            c.setAutoCommit(false);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void commit(){
        try{
            System.out.println("~~~~事务已经被提交");
            jdbcThreadUtil.getConnection().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 用于清除threadlocal中的对应连接，此时连接已经被Dao层close
     */
    public void release(){
        try{
            System.out.println("~~~~事务已经被释放");
            jdbcThreadUtil.removeThreadConnection();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void rollback(){
        try {
            System.out.println("~~~~事务回滚");
            jdbcThreadUtil.getConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
