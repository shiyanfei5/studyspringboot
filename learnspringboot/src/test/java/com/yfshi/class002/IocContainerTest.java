package com.yfshi.class002;

import com.yfshi.class002.car.Audi;
import com.yfshi.class002.car.Buick;
import com.yfshi.class002.human.HumanWithCar;
import com.yfshi.class002.human.Humen;
import com.yfshi.class002.human.LiSi;
import com.yfshi.class002.human.Zhangsan;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IocContainerTest {

    private IocContainer iocContainer = new IocContainer();

    @Before
    public void getBean() {
        System.out.println("before working");
        iocContainer.setBean(Audi.class,"audi");
        iocContainer.setBean(Buick.class,"buick");
        iocContainer.setBean(Zhangsan.class,"zhangsan","audi");
        iocContainer.setBean(LiSi.class,"lisi","buick");
    }

    @Test
    public void setBean() {
        Humen zhangsan = (Humen) iocContainer.getBean("zhangsan");
        zhangsan.goHome();
    }
}