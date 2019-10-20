package aop;

import aop.service.IUserService;

public class TestAop {

    public static void main(String[] args) throws Exception{
        IUserService service =
                (IUserService) BeanFactory.getBean("aop.service.impl.UserServiceImpl");
        System.out.println(service.getClass().getName());
        service.test();
    }
}
