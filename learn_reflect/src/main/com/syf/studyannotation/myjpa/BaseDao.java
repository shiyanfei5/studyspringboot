package main.com.syf.studyannotation.myjpa;


import main.com.syf.studyannotation.datasource.MyDataSource;
import main.com.syf.studyannotation.myjpa.jpaannotation.Colomn;
import main.com.syf.studyannotation.myjpa.jpaannotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务Dao层 继承BaseDao，调用其中方法，实现业务,所以可以获得T类型
 * @param <T>
 */
public class BaseDao<T> {

    private MyDataSource dataSource = new MyDataSource();
    private Class<T> clazz;

    public BaseDao(){
        //初始化claszz
        clazz = (Class) ( (ParameterizedType) this.getClass().getGenericSuperclass() )
                    .getActualTypeArguments()[0];
    }

    public Integer add(T bean){
        //自动添加bean对象
        //首先获取table注解
        Table a = clazz.getAnnotation(Table.class);
        String table = a.value();
        StringBuffer sql = new StringBuffer( "insert into "+ table + " (" );
        StringBuffer valueSql = new StringBuffer("  ) values (");

        Field[] fields = clazz.getDeclaredFields();
        List<Object> insertValue = new ArrayList<>();
        try {
            for (Field item : fields) {
                Colomn b = item.getAnnotation(Colomn.class);
                if (b != null) {
                    sql.append(b.value() + ",");
                    valueSql.append("?,");
                    item.setAccessible(true);
                    insertValue.add(item.get(bean));
                }
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
        sql.deleteCharAt( sql.length()-1);
        valueSql.deleteCharAt( valueSql.length()-1);

        String res = sql.toString() + valueSql.toString()+");";
        System.out.println(res);
        return executeUpdate(res, insertValue.toArray());
    }

    private static void close(ResultSet rs,PreparedStatement ps, Connection c){

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

    public List<T> executeQuery(String sql, IRowMapper rowMapper,Object...args) {
        //首先初始化结果集
        List<T> queryResult = new ArrayList<>();
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            //设置参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i, args[i]);
            }
            //设置结果集
            rs = ps.executeQuery();
            while (rs.next()) {
                T i = (T) rowMapper.mapRow(rs);
                queryResult.add(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //释放资源
            close(rs,ps,conn);
        }

        return queryResult;
    }

    public Integer executeUpdate(String sql, Object...args){
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            //设置参数,sql传参从1开始
            for (int i = 1; i <= args.length; i++) {
                ps.setObject(i, args[i-1]);
            }
            return ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //释放资源
            close(rs,ps,conn);
        }
        return -1;
    }






}
