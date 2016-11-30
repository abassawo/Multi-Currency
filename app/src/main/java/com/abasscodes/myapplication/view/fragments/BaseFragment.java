package com.abasscodes.myapplication.view.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.abasscodes.myapplication.R;
import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.api.ApiClient;
import com.abasscodes.myapplication.model.api.Rates;

/**
 * Created by C4Q on 11/25/16.
 */

public abstract class BaseFragment extends Fragment implements ApiClient.Listener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ApiClient.getInstance(this).getConversionMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        ApiClient.getInstance(this).getConversionMap();
    }

    public int getMessagingStringRes() {
        boolean showAll = PreferenceHelper.showAllCurrenciesOrNot(getActivity());
        if (showAll) {
            return R.string.show_default_currencies;
        } else {
            return R.string.show_all_currencies;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.settings)
                        .setMessage(getMessagingStringRes())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                PreferenceHelper.toggleShowAll(getActivity());
                                onResume();
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // No action - Dismiss.
                    }
                });
                builder.show();
                break;
        }
        return true;
    }


}
