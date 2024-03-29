package com.abasscodes.myapplication.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.model.RateDictionary;
import com.abasscodes.myapplication.model.api.ApiClient;
import com.abasscodes.myapplication.view.RatesAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/18/16.
 */

public class RatesListFragment extends BaseFragment{
    private static final String TAG = RatesListFragment.class.getSimpleName();
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private RatesAdapter adapter;

    private static RatesListFragment instance;
    private RateDictionary dict;


    @Override
    public void onResume() {
        super.onResume();
        ApiClient.getInstance(this).getConversionMap();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RatesAdapter(getActivity());

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);
        ButterKnife.bind(this, view);
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        rv.setLayoutManager(glm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
               glm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);
        rv.setAdapter(adapter);
        return view;
    }

    public void setupRV(RateDictionary dict) {
        adapter.setData(dict);
        Log.d(TAG, "adapter " + adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onConversionComplete(RateDictionary dict) {
        if(dict != null) {
            setupRV(dict);
        }
    }

}
