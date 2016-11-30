package com.abasscodes.myapplication;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.abasscodes.myapplication.helpers.PreferenceHelper;
import com.abasscodes.myapplication.model.RateDictionary;
import com.abasscodes.myapplication.model.api.ApiClient;
import com.abasscodes.myapplication.model.api.Rates;
import com.abasscodes.myapplication.view.BaseFragment;
import com.abasscodes.myapplication.view.CalculatorFragment;
import com.abasscodes.myapplication.view.RatesListFragment;
import com.abasscodes.myapplication.view.TabAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    private TabAdapter adapter;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupActionBar();
        adapter = new TabAdapter(this);
        if (viewPager != null) {
            initViewPager(viewPager);
        }
        tabs.setupWithViewPager(viewPager);
    }


    public void setupNavBar(NavigationView nav) {
        nav.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_TITLE |
                ActionBar.DISPLAY_SHOW_HOME);
        ab.setSubtitle("Base Currency " + PreferenceHelper.getBaseCurrency());
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    public void initViewPager(ViewPager viewPager) {
        adapter.addFragment(new RatesListFragment(), "Rates");
        adapter.addFragment(new CalculatorFragment(), "Calculate");
        viewPager.setAdapter(adapter);
    }



}
