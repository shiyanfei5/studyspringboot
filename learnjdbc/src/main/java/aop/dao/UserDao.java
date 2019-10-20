package aop.dao;

import aop.entity.User;
import jdbc_final.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private BaseDao baseDao = new BaseDao();

    public Integer add(User user){
        String sql = "insert into user(name,age,birthday) values(?,?,?)";
        return this.baseDao.executeUpdate(sql,
                user.getName(),
                user.getAge(),
                user.getbirthday()
        );
    }

    public List<User> findUserByName(String name) {
        String sql = " select * from user where name = ?";
        List<User> userList =  this.baseDao.executeQuery(
                sql,
                User.class,
                new IRowMapper() {
                    @Override
                    public Object mapRow(ResultSet rs) {
                        User item = new User();
                        try{
                            item.setId(rs.getInt("id"));
                            item.setAge(rs.getInt("age"));
                            item.setbirthday(rs.getDate("birthday"));
                            item.setName(rs.getString("name"));
                            return item;
                        } catch (SQLException e){
                            throw  new DaoException(e.getMessage(),e);
                        }
                    }
                },
                name);
        return userList; //不存在时空列表
    }

}
