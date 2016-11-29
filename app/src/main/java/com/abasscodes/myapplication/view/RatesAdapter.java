package com.abasscodes.myapplication.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.helpers.PermissionMap;
import com.abasscodes.myapplication.model.RateDictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by C4Q on 11/18/16.
 */
public class RatesAdapter extends RecyclerView.Adapter<RVViewHolder> {

    private List<Double> rateList; //gbp, eur, jpy, brl;
    private List<String> currenciesList = new ArrayList<>();
    private RateDictionary dict;


    public RatesAdapter() {
        rateList = new ArrayList<>();
        currenciesList = new ArrayList<>();
    }


    public void setData(RateDictionary dict, boolean showAll) {
        this.dict = dict;
        int idx = 0;
        if (showAll) {
            for (String curr : dict.map.keySet()) {
                addCountryAndRate(idx++, curr, dict.get(curr));
            }
        } else {
            for (String curr : dict.map.keySet()) {
                if (new PermissionMap().isPermitted(curr) ) {
                    addCountryAndRate(idx++, curr, dict.map.get(curr));
                }
            }
        }


    }


    public void addCountryAndRate(int idx, String currency, double rate) {
        currenciesList.add(idx, currency);
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
        String country = currenciesList.get(position);
        double rate = rateList.get(position);
        holder.bind(country, rate);
    }

    @Override
    public int getItemCount() {
        if (rateList.isEmpty() || currenciesList.isEmpty()) return 0;
        return rateList.size();
    }


}
