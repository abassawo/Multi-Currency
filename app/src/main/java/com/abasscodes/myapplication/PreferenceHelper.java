package com.abasscodes.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public static Set<String> getDefaultCurrencies(){
        defaultCurrencies = new HashSet<>();
        defaultCurrencies.addAll(Arrays.asList(defaultCurrArray));
        return defaultCurrencies;
    }

    public static Set<String> getFollowedCurrencies(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getStringSet("selectedCurrencies", getDefaultCurrencies());
    }

    public static void addToFollowCurrencies(Context context, Set<String> currencies){
        Set<String> follows = getDefaultCurrencies();
        follows.addAll(currencies);
        PreferenceManager.getDefaultSharedPreferences(context).edit().putStringSet("selectedCurrencies", follows).commit();
    }
}
