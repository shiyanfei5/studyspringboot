package com.yfshi.class002.car;

import com.yfshi.class002.car.Car;

public class Buick implements Car {
    @Override
    public void start() {
        System.out.println(this.getClass().getSimpleName()+"start");
    }

    @Override
    public void turnLeft() {
        System.out.println(this.getClass().getSimpleName()+"turn left");
    }

    @Override
    public void turnRight() {
        System.out.println(this.getClass().getSimpleName()+"turn right");
    }

    @Override
    public void stop() {
        System.out.println(this.getClass().getSimpleName()+"stop");
    }

}
