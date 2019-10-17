package jdbc_final.dao;

import jdbc_final.entity.User;

import java.util.List;

public interface IUserDao {

    int addUser(User user);
    int update(User user);
    int delete(Integer id);
    User getUser(int Id);
    List<User> findUserByName(String name);
}
