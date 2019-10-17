package jdbc_final.dao.impl;


import jdbc_final.dao.BaseDao;
import jdbc_final.dao.IRowMapper;
import jdbc_final.dao.IUserDao;
import jdbc_final.entity.User;
import jdbc_final.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    BaseDao baseDao = new BaseDao();

    @Override
    public int addUser(User user) {
        String sql = "insert into user(name,age,birthday) values(?,?,?)";
        return this.baseDao.executeUpdate(sql,
                user.getName(),
                user.getAge(),
                user.getbirthday()
        );
    }

    @Override
    public int update(User user) {
        String sql = " update user set name = ? ,age = ? , birthday = ? where id = ?";
        return this.baseDao.executeUpdate(sql,
                user.getName(),
                user.getAge(),
                user.getbirthday(),
                user.getId()
        );
    }

    @Override
    public int delete(Integer id) {
        String sql = " delete from user where id = ?";
        return this.baseDao.executeUpdate(sql,id);
    }

    @Override
    public User getUser(int id) {
        String sql = " select * from user where id = ?";
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
                id);
        return userList.size()>0? userList.get(0):null; //返回第一个不存在返回null
    }

    @Override
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
