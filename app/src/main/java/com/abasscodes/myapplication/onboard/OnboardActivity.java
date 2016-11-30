package com.abasscodes.myapplication.onboard;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.abasscodes.myapplication.MainActivity;
import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.MessageButtonBehaviour;
import agency.tango.materialintroscreen.SlideFragment;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

/**
 * Created by C4Q on 11/23/16.
 */

public class OnboardActivity extends MaterialIntroActivity {
    SlideFragmentBuilder builder = new SlideFragmentBuilder();
    private MessageButtonBehaviour buttonBehavior;
    private IViewTranslation translation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        translation = new IViewTranslation() {
            @Override
            public void translate(View view, @FloatRange(from = 0.0, to = 1.0) float percentage) {
                PreferenceHelper.disableWelcome(getApplicationContext());
                startActivity(new Intent(OnboardActivity.this, MainActivity.class));
            }
        };
        buttonBehavior = new MessageButtonBehaviour(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage(getResources().getString(R.string.app_description));
            }
        }, getResources().getString(R.string.app_name));
        initSlides();

    }

    public void initSlides() {
        enableLastSlideAlphaExitTransition(true);
        getNextButtonTranslationWrapper().setExitTranslation(translation);
        addSlide(new SlideFragmentBuilder()
                .backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.colorAccent)
                .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                .title("Let's get started!")
                .description(getResources().getString(R.string.fixer_credit))
                .build(), buttonBehavior);
        addSlide(new SlideFragmentBuilder()
                .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                .backgroundColor(R.color.colorPrimary)
                .buttonsColor(R.color.colorAccent)
                .title("View exchange rates or Convert currencies")
                .build(), buttonBehavior);
        addLastSlide();
    }

    public void addLastSlide() {
        addSlide(builder.backgroundColor(R.color.cardview_dark_background)
                .buttonsColor(R.color.blueish_grey)
                .image(agency.tango.materialintroscreen.R.drawable.ic_next)
                .title("You're all set.")
                .description("Start using Multi-Currency")
                .build(), buttonBehavior);
    }


}
