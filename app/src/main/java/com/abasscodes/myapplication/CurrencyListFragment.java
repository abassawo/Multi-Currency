package com.abasscodes.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.model.Currencies;
import com.abasscodes.myapplication.model.api.ApiClient;
import com.abasscodes.myapplication.model.api.Rates;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */

public class CurrencyListFragment extends Fragment implements ApiClient.Listener {
    private static final String TAG = CurrencyListFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private RatesAdapter adapter;

    private static CurrencyListFragment instance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiClient.getInstance(this).getConversionMap();
        adapter = new RatesAdapter();

    }

    public static CurrencyListFragment getInstance() {
        if (instance == null) {
            instance = newInstance();
        }
        return instance;
    }

    private static CurrencyListFragment newInstance() {
        Bundle args = new Bundle();
        CurrencyListFragment fragment = new CurrencyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        return view;
    }

    public void setupRV(Rates rates) {
        Log.d(TAG + " RATES ", rates.getBRL() + "" + rates.getGBP() + "" + rates.getJPY() + rates.getEUR());
        adapter.setRates(rates);
        Log.d(TAG, "adapter " + adapter.getItemCount());
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }


    @Override
    public void onConversionComplete(Rates rates) {
        setupRV(rates);
    }
}
