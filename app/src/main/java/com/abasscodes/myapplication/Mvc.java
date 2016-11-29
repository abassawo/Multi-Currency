package com.abasscodes.myapplication;

/**
 * Created by C4Q on 11/18/16.
 */

public interface Mvc {

    public interface Model {
       double exchangeTo(Model model);
        double getExchangeRate(Model model);
    }

    public interface View{

    }

    public interface Controller{
        double findExchangeRate(double usd);
    }
}
