package com.abasscodes.myapplication.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by C4Q on 11/29/16.
 */

public class PermissionMap {

    private Map<String, Boolean> permission = new HashMap<>();


    public PermissionMap(){
        permission.put("EUR", true);
        permission.put("JPY", true);
        permission.put("BRL", true);
        permission.put("GBP", true);
    }

    public void applyPermission(String currency, boolean value) {
        permission.put(currency, value);
    }

    public boolean isPermitted(String curr) {
        if(permission.containsKey(curr)){
            return permission.get(curr);
        }
        return false;
    }
}
