package com.abasscodes.myapplication.model;

/**
 * Created by C4Q on 11/18/16.
 */

public class Currency {
    private String name;
    private double exchangeRate;

    public Currency(String name, double exchangeRate){
        this.name = name;
        this.exchangeRate = exchangeRate;
    }


    public String getName() {
        return name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
