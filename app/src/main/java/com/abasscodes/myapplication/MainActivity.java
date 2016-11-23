package com.abasscodes.myapplication;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.abasscodes.myapplication.model.api.Rates;

import butterknife.BindView;
import butterknife.ButterKnife;

;import static com.abasscodes.myapplication.R.id.spinner;

public class MainActivity extends AppCompatActivity implements TabAdapter.Callback{

    private TabAdapter adapter;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.spinner_toolbar)
    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupActionBar();
        setupNavBar(navView);
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
        ab.setDisplayShowTitleEnabled(false);
    }



    public void initViewPager(ViewPager viewPager) {
        adapter.addFragment(new RatesListFragment(), "Rates");
//        adapter.addFragment(new CalculatorFragment(), "Calculate");
        viewPager.setAdapter(adapter);
    }

    public void onRatesLoaded(Rates rates){
        adapter.addFragment(CalculatorFragment.newInstance(rates), "Calculate");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTabFocus(int position) {
        switch (position){
            case 0: toolbarTitle.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.INVISIBLE);
                toolbarTitle.setVisibility(View.VISIBLE);
                break;
            case 1:toolbarTitle.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
                toolbarTitle.setVisibility(View.INVISIBLE);
        }
    }
}
