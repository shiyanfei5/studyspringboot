package jdbc_2.dao.impl;

import jdbc_2.dao.IUserDao;
import jdbc_2.entity.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;





public class UserDaoImplTest {

    @Test
    public void testcrud(){
        User user = new User("shiyanfei2",38, new Date(1980-1900,01,30));
        user.setId(2);
        IUserDao userDao = new UserDaoImpl();
//        userDao.addUser(user);
//        userDao.delete(1);
        userDao.update(user);




    }




}