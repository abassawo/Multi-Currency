package com.abasscodes.myapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.RateCalculator;
import com.abasscodes.myapplication.helpers.TextUtil;
import com.abasscodes.myapplication.model.RateDictionary;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;
import com.abasscodes.myapplication.model.api.Rates;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */
public class CalculatorFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.base_currency_edittext)
    EditText baseField;
    @BindView(R.id.result_textview)
    TextView resultField;
    @BindView(R.id.convert_btn)
    Button convertBtn;
    @BindView(R.id.spinner)
    Spinner spinner;

    private RateCalculator calculator;
    CurrenciesSupported targetCurrency;
    private ArrayAdapter<CurrenciesSupported> adapter;
    private CurrenciesSupported[] currencies;
    private static String DICT_KEY = "dictionary";

    public static CalculatorFragment newInstance(RateDictionary dictionary) {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DICT_KEY, dictionary);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reload();
    }

    public void initCalculator(RateDictionary dictionary) {
        calculator = new RateCalculator(dictionary);
        targetCurrency = currencies[0];
    }

    public void initListeners() {
        convertBtn.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                targetCurrency = currencies[i];
                if (!TextUtil.isEmpty(baseField))
                    updateAnswer(calculator.convertTo(targetCurrency, Double.parseDouble(baseField.getText().toString())));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void updateAnswer(double result) {
        resultField.setText(String.valueOf(result));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, view);
        spinner.setAdapter(adapter);
        spinner.setClickable(true);
        Arrays.sort(currencies);
        initListeners();
        return view;
    }


    public void reload() {
        RateDictionary dict = RateDictionary.getInstance();
        currencies = PreferenceHelper.getCurrenciesForPermission(getActivity());
        initCalculator(dict);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, currencies);
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

    @Override
    public void onConversionComplete(RateDictionary dict) {
        initCalculator(dict);
    }

}
