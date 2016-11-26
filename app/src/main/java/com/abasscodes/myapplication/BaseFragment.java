package com.abasscodes.myapplication;

import android.support.v4.app.Fragment;

/**
 * Created by C4Q on 11/25/16.
 */

public abstract class BaseFragment extends Fragment {

    public abstract void reload();

    public interface FragmentCallback{
        void reload(int tabIndex);
    }
}
