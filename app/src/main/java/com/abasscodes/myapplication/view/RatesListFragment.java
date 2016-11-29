package com.abasscodes.myapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.MainActivity;
import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.RateDictionary;
import com.abasscodes.myapplication.model.api.ApiClient;
import com.abasscodes.myapplication.model.api.Rates;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */

public class RatesListFragment extends BaseFragment implements ApiClient.Listener {
    private static final String TAG = RatesListFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private RatesAdapter adapter;

    private static RatesListFragment instance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiClient.getInstance(this).getConversionMap();
        adapter = new RatesAdapter();
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

    public void setupRV(RateDictionary dict) {
        boolean showAll = PreferenceHelper.showAllCurrenciesOrNot(getActivity());
        adapter.setData(dict, showAll);
        Log.d(TAG, "adapter " + adapter.getItemCount());
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }


    @Override
    public void onConversionComplete(Rates rates) {
        RateDictionary dict = new RateDictionary(rates);
        setupRV(dict);
        MainActivity activity = (MainActivity) getActivity();
        activity.onRatesLoaded(rates);
    }

    @Override
    public void reload() {

    }
}
