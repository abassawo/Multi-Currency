package com.abasscodes.myapplication.helpers;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by C4Q on 11/22/16.
 */

public class TextUtil {

    public static boolean isEmpty(EditText editText){
        return TextUtils.isEmpty(editText.getText().toString());
    }
}
