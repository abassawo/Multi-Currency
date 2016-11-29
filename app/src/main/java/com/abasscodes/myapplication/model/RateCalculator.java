package com.abasscodes.myapplication.model;

import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.RateDictionary;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;
import com.abasscodes.myapplication.model.api.Rates;

/**
 * Created by C4Q on 11/22/16.
 */

public class RateCalculator {

    private RateDictionary dictionary;

    public RateCalculator(Rates rates){
        this(new RateDictionary(rates));
    }

    private RateCalculator(RateDictionary dictionary) {
        this.dictionary = dictionary;
    }


    public double getConversionFromCurrentBase(CurrenciesSupported currency) {
        String base = PreferenceHelper.getBaseCurrency();
        return getConversionRate(base, currency);
    }

    public double convertTo(CurrenciesSupported currency, double originalAmt) {
        double rate = getConversionFromCurrentBase(currency);
        return originalAmt * rate;
    }

    private double getConversionRate(String base, CurrenciesSupported currency) {
        if (dictionary.baseCurrency.equals(base))
            return dictionary.get(currency.name());
        else return 0;
    }


}
