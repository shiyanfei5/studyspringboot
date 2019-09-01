package com.yfshi.class002.human;

import com.yfshi.class002.car.Car;

public abstract  class HumanWithCar implements Humen{
    protected Car car;  //必须是protected

    public HumanWithCar(Car car){
        this.car = car;
    }

    public abstract void goHome();  //虚类
}
