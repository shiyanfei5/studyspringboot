package com.syf.dynamicproxy.impl;

import com.syf.dynamicproxy.ICaculator;

public class CalculatorImpl implements ICaculator {

    public int add(int a , int b){
        return a+b;
    }


    public int subtract(int a , int b){
        return a-b;
    }
}
