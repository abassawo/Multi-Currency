package com.abasscodes.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
<<<<<<< HEAD
=======
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
>>>>>>> parent of 9241cdb... set up currency spinner
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.model.FixerDictionary;
import com.abasscodes.myapplication.model.api.ApiClient;
import com.abasscodes.myapplication.model.api.Rates;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */

public class RatesListFragment extends Fragment implements ApiClient.Listener {
    private static final String TAG = RatesListFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private RatesAdapter adapter;

    private static RatesListFragment instance;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ApiClient.getInstance(this).getConversionMap();
        adapter = new RatesAdapter(PreferenceHelper.getDefaultCurrencies());
    }
<<<<<<< HEAD
=======

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_rates, menu);
    }
>>>>>>> parent of 9241cdb... set up currency spinner

    public static RatesListFragment getInstance() {
        if (instance == null) {
            instance = newInstance();
        }
        return instance;
    }

    private static RatesListFragment newInstance() {
        Bundle args = new Bundle();
        RatesListFragment fragment = new RatesListFragment();
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

    public void setupRV(Rates rates, Set<String> currencies) {
        Log.d(TAG + " RATES ", rates.getBRL() + "" + rates.getGBP() + "" + rates.getJPY() + rates.getEUR());
        adapter.setData(currencies, new FixerDictionary(rates));
        adapter.notifyDataSetChanged();
        Log.d(TAG, "adapter " + adapter.getItemCount());
//        rv.setAdapter(adapter);
    }


    @Override
    public void onConversionComplete(Rates rates) {
        Set<String> currencies = PreferenceHelper.getFollowedCurrencies(getActivity());
        setupRV(rates, currencies);
        MainActivity activity = (MainActivity) getActivity();
        activity.onRatesLoaded(rates);
    }

}
