package jdbc_2.dao.impl;

import jdbc_2.dao.IUserDao;
import jdbc_2.entity.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;





public class UserDaoImplTest {

    @Test
    public void testcrud(){
        User user = new User("rju",29, new Date(1990-1900,01,30));

        IUserDao userDao = new UserDaoImpl();

        userDao.addUser(user);
        System.out.println(userDao.findUserByName("rju"));
        System.out.println(userDao.getUser(4));



    }




}