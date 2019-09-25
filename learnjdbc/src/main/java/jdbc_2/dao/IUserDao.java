package jdbc_2.dao;

import jdbc_2.entity.User;

public interface IUserDao {

    int addUser(User user);
    int update(User user);
    int delete(Integer id);
    User getUser(int Id);
    User findUser(String name , int age);
}
