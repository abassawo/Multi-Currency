package com.abasscodes.myapplication;

import com.abasscodes.myapplication.model.Currencies;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;
import com.abasscodes.myapplication.model.api.Rates;

/**
 * Created by C4Q on 11/22/16.
 */

public class RateCalculator {
    private Rates rates;

    public RateCalculator(Rates rates) {
        this.rates = rates;
    }

    public double getConversionFromUSBase(CurrenciesSupported currency){
        return getConversionRate("USD", currency);
    }

    public double getConversionFromCurrentBase(CurrenciesSupported currency){
        String base = PreferenceHelper.getBaseCurrency();
        return getConversionRate(base ,currency);
    }

    public double convertTo(CurrenciesSupported currency, double originalAmt) {
        double rate = getConversionFromCurrentBase(currency);
        return originalAmt * rate;
    }

    private double getConversionRate(String base, CurrenciesSupported currency) {
        switch (currency) {
            case GBP:
                return rates.getGBP();
            case JPY:
                return rates.getJPY();
            case BRL:
                return rates.getBRL();
            case AUD:
                return rates.getAUD();
            case BGN:
                return rates.getBGN();
            case CAD:
                return rates.getCAD();
            case CHF:
                return rates.getCHF();
            case CNY:
                return rates.getCNY();
            case CZK:
                return rates.getCZK();
            case DKK:
                return rates.getDKK();
            case HKD:
                return rates.getHKD();
            case HRK:
                return rates.getHRK();
            case HUF:
                return rates.getHUF();
            case IDR:
                return rates.getIDR();
            case ILS:
                return rates.getILS();
            case INR:
                return rates.getINR();
            case KRW:
                return rates.getKRW();
            case MXN:
                return rates.getMXN();
            case MYR:
                return rates.getMYR();
            case NOK:
                return rates.getNOK();
            case NZD:
                return rates.getNZD();
            case PHP:
                return rates.getPHP();
            case PLN:
                return rates.getPLN();
            case RON:
                return rates.getRON();
            case RUB:
                return rates.getRUB();
            case SEK:
                return rates.getSEK();
            case SGD:
                return rates.getSGD();
            case THB:
                return rates.getTHB();
            case TRY:
                return rates.getTRY();
            case ZAR:
                return rates.getZAR();
            case EUR:
                return rates.getEUR();
        }
        return 0;
    }


}
