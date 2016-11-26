package com.abasscodes.myapplication.ui.onboard;

import com.abasscodes.myapplication.R;

import agency.tango.materialintroscreen.SlideFragment;

/**
 * Created by C4Q on 11/23/16.
 */
public class BaseSlideFragment extends SlideFragment{

    @Override
    public int backgroundColor() {
        return R.color.colorPrimary;
    }


    @Override
    public int buttonsColor() {
        return R.color.colorAccent;
    }

    @Override
    public boolean canMoveFurther() {
        return true;
    }

    @Override
    public String cantMoveFurtherErrorMessage() {
        return getString(R.string.error_message);
    }

}
