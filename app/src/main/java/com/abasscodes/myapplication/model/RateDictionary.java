package com.abasscodes.myapplication.model;

import com.abasscodes.myapplication.model.api.Rates;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by C4Q on 11/29/16.
 */

public class RateDictionary {
    public String baseCurrency;
    public Map<String, Double> map = new HashMap<>();

    public RateDictionary(Rates rates){
        this("USD", rates);
    }

    private RateDictionary(String baseCurr, Rates rates) {
        this.baseCurrency = baseCurr;
        map.put("AUD", rates.getAUD());
        map.put("BGN", rates.getBGN());
        map.put("BRL", rates.getBRL());
        map.put("CAD", rates.getCAD());
        map.put("CHF", rates.getCHF());
        map.put("CNY", rates.getCNY());
        map.put("CZK", rates.getCZK());
        map.put("DKK", rates.getDKK());
        map.put("GBP", rates.getGBP());
        map.put("HKD", rates.getHKD());
        map.put("HRK", rates.getHRK());
        map.put("HUF", rates.getHUF());
        map.put("IDR", rates.getIDR());
        map.put("ILS", rates.getILS());
        map.put("INR", rates.getINR());
        map.put("JPY", rates.getJPY());
        map.put("KRW", rates.getKRW());
        map.put("MXN", rates.getMXN());
        map.put("MYR", rates.getMYR());
        map.put("NOK", rates.getNOK());
        map.put("NZD", rates.getNZD());
        map.put("PHP", rates.getPHP());
        map.put("PLN", rates.getPLN());
        map.put("RON", rates.getRON());
        map.put("RUB", rates.getRUB());
        map.put("SEK", rates.getSEK());
        map.put("SGD", rates.getSGD());
        map.put("THB", rates.getTHB());
        map.put("TRY", rates.getTRY());
        map.put("ZAR", rates.getZAR());
        map.put("EUR", rates.getEUR());
    }

    public double getGBP() {
        return map.get("GBP");
    }

    public double getEUR() {
        return map.get("EUR");
    }

    public double getJPY(){
        return map.get("JPY");
    }

    public double getBRL(){
        return map.get("BRL");
    }

    public double get(String curr) {
        return map.get(curr);
    }
}
