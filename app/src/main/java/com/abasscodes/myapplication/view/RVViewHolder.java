package com.abasscodes.myapplication.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.model.Currency;


import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */

public class RVViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.currency_name)
 TextView currencyNameField;
    @BindView(R.id.currency_value)
 TextView currencyValueField;

    public RVViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }



    public void bind(String country, double rate) {
        DecimalFormat df = new DecimalFormat("####0.00");
        currencyNameField.setText(country);
        currencyValueField.setText(df.format(rate));
    }
}
