package jdbc_2.dao.impl;

import jdbc_2.dao.AbstractDao;
import jdbc_2.dao.IUserDao;
import jdbc_2.entity.User;

public class UserDaoImpl extends AbstractDao implements IUserDao {

    @Override
    public int addUser(User user) {
        String sql = "insert into user(name,age,birthday) values(?,?,?)";
        return this.executeUpdate(sql,user.getName(),user.getAge(),user.getbirthday());
    }

    @Override
    public int update(User user) {
        String sql = " update user set name = ? ,age = ? , birthday = ? where id = ?";
        return this.executeUpdate(sql,user.getName(),user.getAge(),user.getbirthday(),user.getId());
    }

    @Override
    public int delete(Integer id) {
        String sql = " delete from user where id = ?";
        return this.executeUpdate(sql,id);
    }

    @Override
    public User getUser(int Id) {
        return null;
    }

    @Override
    public User findUser(String name, int age) {
        return null;
    }
}
