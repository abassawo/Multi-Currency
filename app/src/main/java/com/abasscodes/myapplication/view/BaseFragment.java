package com.abasscodes.myapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.abasscodes.myapplication.R;

/**
 * Created by C4Q on 11/25/16.
 */

public abstract class BaseFragment extends Fragment {

    public abstract void reload();

    public interface FragmentCallback{
        void reload(int tabIndex);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.reload:
                break;
        }
        return true;
    }
}
