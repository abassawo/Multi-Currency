package com.abasscodes.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.model.Currencies;
import com.abasscodes.myapplication.model.Currency;
import com.abasscodes.myapplication.model.api.Rates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/18/16.
 */
public class RatesAdapter extends RecyclerView.Adapter<RVViewHolder> {
    private Rates rates;

    private List<Double> rateList; //gbp, eur, jpy, brl;
    private List<String> countryNames;


    public RatesAdapter(){
        rateList = new ArrayList<>();
        countryNames = new ArrayList<>();
    }

    public void setRates(Rates rates){
        this.rates = rates;
        addCountryAndRate(0, "UK", rates.getGBP());
        addCountryAndRate(1, "Euro", rates.getEUR());
        addCountryAndRate(2, "Japan", rates.getJPY());
        addCountryAndRate(3, "Brazil", rates.getBRL());
    }

    public void addCountryAndRate(int idx, String country, double rate){
        countryNames.add(idx, country);
        rateList.add(idx, rate);

    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        View view = infl.inflate(R.layout.row_item, parent, false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVViewHolder holder, int position) {
        String country = countryNames.get(position);
        double rate = rateList.get(position);
        holder.bind(country, rate);
    }

    @Override
    public int getItemCount() {
        if(rateList.isEmpty() || countryNames.isEmpty()) return 0;
        return rateList.size();
    }
}
