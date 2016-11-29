package com.abasscodes.myapplication.ui.onboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.api.CurrenciesSupported;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by C4Q on 11/23/16.
 */

public class PickerFragment extends BaseSlideFragment {

    @BindView(R.id.switch_btn)
    SwitchCompat switchBtn;
    private OptionsAdapter adapter;
//    @BindView(R.id.done_btn)
//    Button doneButton;

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
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                PreferenceHelper.setShowAllCurrencies(getActivity(), b);
            }
        });
        return view;
    }


    public void setupRV(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }



}
