package com.abasscodes.myapplication.helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.abasscodes.myapplication.MainActivity;
import com.abasscodes.myapplication.ui.onboard.OnboardActivity;

/**
 * Created by C4Q on 11/23/16.
 */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        if(PreferenceHelper.isFirstRun(this)){
            intent = new Intent(this, OnboardActivity.class);
        }else{
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }




}
