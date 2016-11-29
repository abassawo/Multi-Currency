package com.abasscodes.myapplication.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by C4Q on 11/22/16.
 */

public class PreferenceHelper {

    private static String onboard_key = "onboard?";
    private static String show_all_currs = "showAll?";
    public static String getBaseCurrency() {
        return "USD";
    }

    private static String[] defaultCurrArray = new String[]{"JPY", "EUR", "BRL", "GBP"};
    private static Set<String> defaultCurrencies;

    public static boolean isFirstRun(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(onboard_key, true);
    }

    public static void disableWelcome(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(onboard_key, false).commit();
    }


    public static void setShowAllCurrencies(Context context, boolean showAll) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(show_all_currs, showAll).commit();
    }

    public static boolean showAllCurrenciesOrNot(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(show_all_currs, false);
    }

    public static void toggleShowAll(Context context) {
        boolean showAll = showAllCurrenciesOrNot(context);
        setShowAllCurrencies(context, !showAll);
    }
}
