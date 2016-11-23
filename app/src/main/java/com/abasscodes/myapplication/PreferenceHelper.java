package com.abasscodes.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by C4Q on 11/22/16.
 */

public class PreferenceHelper {
    private static String onboard_key = "onboard?";

    public static String getBaseCurrency() {
        return "USD";
    }

    public static boolean isFirstRun(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(onboard_key, true);
    }

    public static void disableWelcome(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean(onboard_key, false).commit();
    }

}
