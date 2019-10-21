package aop.service.impl;

import aop.dao.UserDao;
import aop.entity.User;
import aop.service.IUserService;
import aop.transation.MyTransation;
import jdbc_final.exception.DaoException;

import java.util.Date;
import java.util.List;


@MyTransation
public class UserServiceImpl implements IUserService {

    private UserDao userDao = new UserDao();    //没有使用spring只能通过手动new形式注入

    @Override
    public void test() {
        List<User> userList = userDao.findUserByName("yfshi");
        System.out.println("--查询结果:"+userList);
        User user = new User("yfshi",99, new Date());
        userDao.add(user);
        userList = userDao.findUserByName("yfshi");
        System.out.println("--查询结果:"+userList);
        throw  new DaoException("aaaa");

    }
}
