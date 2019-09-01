package com.yfshi.class002.human;

import com.yfshi.class002.car.Car;

public class LiSi extends HumanWithCar {

    public LiSi(Car car){
        super(car);
    }

    public void goHome(){
        car.start();
        car.stop();
    }

}
