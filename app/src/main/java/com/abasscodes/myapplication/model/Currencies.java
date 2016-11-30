package com.abasscodes.myapplication.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by C4Q on 11/18/16.
 */

public class Currencies  {
    private List<Currency> currencies;

    public Currencies(){
        this.currencies = new ArrayList<>();
    }

    public Currencies(List<Currency> currencyList){
        this.currencies = currencyList;
    }

    public Currency get(int index){
        return currencies.get(index);
    }

    public int size(){
        return currencies.size();
    }


    public void add(Currency currency) {
        currencies.add(currency);
    }
}
