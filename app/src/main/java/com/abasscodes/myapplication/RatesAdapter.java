package com.abasscodes.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.model.FixerDictionary;
import com.abasscodes.myapplication.model.api.Rates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by C4Q on 11/18/16.
 */
public class RatesAdapter extends RecyclerView.Adapter<RVViewHolder> {
    private Rates rates;

    private List<Double> rateList; //gbp, eur, jpy, brl;
    private List<String> countryNames;
    private Set<String> currencies = new HashSet<>();

    private FixerDictionary dictionary;


    public RatesAdapter(Set<String> currencies){
        rateList = new ArrayList<>();
        this.currencies = currencies;
        countryNames = new ArrayList<>();
    }

    public void setData(Set<String> currencies, FixerDictionary dictionary){
        this.currencies = currencies;
        setDictionary(dictionary);
    }


    private void setDictionary(FixerDictionary dictionary){
        this.dictionary = dictionary;
        int idx = 0;
        for(String curr : currencies){
//            if (FixerDictionary.getPermission(curr)) {
                double exch = dictionary.map.get(curr);
                addCountryAndRate(idx++, curr, exch);
//            }
        }
    }


    public void addCountryAndRate(int idx, String curr, double rate){
        countryNames.add(idx, curr);
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
        return countryNames.size();
    }


}
