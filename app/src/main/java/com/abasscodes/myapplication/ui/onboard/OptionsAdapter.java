package com.abasscodes.myapplication.ui.onboard;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.abasscodes.myapplication.model.FixerDictionary;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        holder.bind(currencies[position]);
    }

    @Override
    public int getItemCount() {
        return currencies.length;

    }

    public Set<String> getSelectedCurrencies() {
        Set<String> currSet = new HashSet<>();
        for (int i = 0; i < currencies.length; i++) {
            if(FixerDictionary.permissionMap.get(currencies[i].name())){
                currSet.add(holder.currencyTV.getText().toString());
            }
        }
        return currSet;
    }




}
