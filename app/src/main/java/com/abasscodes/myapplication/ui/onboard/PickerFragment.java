package com.abasscodes.myapplication.ui.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;


import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/23/16.
 */

public class PickerFragment extends BaseSlideFragment implements View.OnClickListener {
    @BindView(R.id.picker_rv) RecyclerView recyclerView;
    @BindView(R.id.select_all_btn)
    ToggleButton toggleBtn;
    private OptionsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new OptionsAdapter(CurrenciesSupported.values());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.picker_fragment, container, false);
        ButterKnife.bind(this, view);
        setupRV(recyclerView);
        return view;
    }

    public void setupRV(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.select_all_btn:
                boolean selectAll = toggleBtn.isChecked();
                adapter.onButtonClicked(selectAll);
                break;
        }
    }

    public interface AdapterCallback {
        void onButtonClicked(boolean selectAll);
    }
}
