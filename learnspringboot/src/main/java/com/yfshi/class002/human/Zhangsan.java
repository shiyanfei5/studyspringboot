package com.yfshi.class002.human;

import com.yfshi.class002.car.Car;

public class Zhangsan extends HumanWithCar{


    public Zhangsan(Car car){
        super(car);
    }

    public void goHome(){
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }


    public void goShop(){
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}
