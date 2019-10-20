package main.com.syf.studyannotation.myjpa.dao;

import main.com.syf.studyannotation.myjpa.BaseDao;
import main.com.syf.studyannotation.myjpa.entity.User;

import java.util.Date;

public class UserDao extends BaseDao<User> {

    public static  void main(String[] args){
        UserDao dao = new UserDao();
        User user = new User("yfshi",100,new Date(),"sss");
        dao.add(user);

        

    }

}
