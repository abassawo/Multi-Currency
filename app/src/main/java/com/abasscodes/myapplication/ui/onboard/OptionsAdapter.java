package com.abasscodes.myapplication.ui.onboard;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.myapplication.model.Currencies;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;
//import com.bignerdranch.android.multiselector.MultiSelector;

import java.util.Arrays;

/**
 * Created by C4Q on 11/23/16.
 */
public class OptionsAdapter extends RecyclerView.Adapter<OptionsViewHolder> {
    public CurrenciesSupported[] currencies;
    private OptionsViewHolder holder;


    public OptionsAdapter(CurrenciesSupported[] values) {
        this.currencies = values;
        Arrays.sort(currencies);
    }

    @Override
    public OptionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        holder = new OptionsViewHolder(parent);
        return holder;
    }

    @Override
    public void onBindViewHolder(OptionsViewHolder holder, int position) {
        holder.bind(currencies[position], false);
    }

    @Override
    public int getItemCount() {
        return currencies.length;
    }

//    @Override
//    public void onButtonClicked(boolean selectAll) {
//        for (CurrenciesSupported c : currencies) {
//            holder.bind(c, selectAll);
//        }
//    }



}
