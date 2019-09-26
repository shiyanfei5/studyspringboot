package jdbc_datasource.dao;

import jdbc_datasource.entity.User;

import java.util.List;

public interface IUserDao {

    int addUser(User user);
    int update(User user);
    int delete(Integer id);
    User getUser(int Id);
    List<User> findUserByName(String name);
}
