package com.abasscodes.myapplication;

import com.abasscodes.myapplication.model.Currency;

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
        void setBaseCurrency(Currency currency);
        double findExchangeRate(double usd);
    }
}
