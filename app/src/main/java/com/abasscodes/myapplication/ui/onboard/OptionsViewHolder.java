package com.abasscodes.myapplication.ui.onboard;

import android.animation.StateListAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.abasscodes.myapplication.model.api.CurrenciesSupported.BRL;
import static com.abasscodes.myapplication.model.api.CurrenciesSupported.EUR;
import static com.abasscodes.myapplication.model.api.CurrenciesSupported.GBP;
import static com.abasscodes.myapplication.model.api.CurrenciesSupported.JPY;

/**
 * Created by C4Q on 11/23/16.
 */
public class OptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.currency_opt_checkbox)
    CheckBox checkBox;
    @BindView(R.id.currency_opt_textview)
    TextView currencyTV;




    public OptionsViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);

    }


    public static View inflateView(ViewGroup parent) {
        LayoutInflater infl = LayoutInflater.from(parent.getContext());
        return infl.inflate(R.layout.option_row_item, parent, false);
    }

    public void bind(CurrenciesSupported currency, boolean checked) {
        currencyTV.setText(currency.name());
        switch (currency){
            case EUR:
            case JPY:
            case BRL:
            case GBP:
                checkBox.setChecked(true);
                checkBox.setClickable(false);
                break;
            default: checkBox.setChecked(checked);
        }

    }

    public boolean isCheckedByDefault(CurrenciesSupported c) {
        return c == EUR || c == JPY || c == BRL || c == GBP;
    }

    @Override
    public void onClick(View view) {

    }


}
