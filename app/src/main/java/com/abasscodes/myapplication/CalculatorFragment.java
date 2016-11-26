package com.abasscodes.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.abasscodes.myapplication.helpers.TextUtil;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;
import com.abasscodes.myapplication.model.api.Rates;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */
public class CalculatorFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.base_currency_edittext)
    EditText baseField;
    @BindView(R.id.result_textview)
    TextView resultField;
    @BindView(R.id.convert_btn)Button convertBtn;
    @BindView(R.id.spinner_toolbar)
    Spinner spinner;

    private RateCalculator calculator;
    final CurrenciesSupported[] currencies = CurrenciesSupported.values();
    CurrenciesSupported targetCurrency;

    public static CalculatorFragment newInstance(Rates rates) {
        Bundle args = new Bundle();
        args.putParcelable("RATES", rates);
        CalculatorFragment fragment = new CalculatorFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
//        setHasOptionsMenu(true);
        Bundle args = getArguments();
        Rates rates = (Rates) args.get("RATES");
        calculator = new RateCalculator(rates);
        targetCurrency = currencies[0];
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_calculate, menu);
//    }

    public void initListeners() {
        convertBtn.setOnClickListener(this);
    }

    public void updateAnswer(double result) {

        resultField.setText(String.valueOf(result));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, view);
        spinner.setClickable(true);
        Arrays.sort(currencies);
        spinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, currencies));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                targetCurrency = currencies[i];
                if(!TextUtil.isEmpty(baseField))
                updateAnswer(calculator.convertTo(targetCurrency, Double.parseDouble(baseField.getText().toString())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        initListeners();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.convert_btn:
                if (TextUtil.isEmpty(baseField)) {
                    showEditTextError();
                    return;
                }
                String editable = baseField.getText().toString();
                double origAmt = Double.parseDouble(editable);
                double result = calculator.convertTo(targetCurrency, origAmt);
                updateAnswer(result);
                break;
        }

    }

    private void showEditTextError() {
        Snackbar.make(getView(), "Add the base currency amount you'd like to convert ", Snackbar.LENGTH_INDEFINITE).show();
    }
}
