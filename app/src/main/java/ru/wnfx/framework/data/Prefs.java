package ru.wnfx.framework.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {

    private final SharedPreferences sp;

    public Prefs(Context context) {
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void put(String name, String value) {
        sp.edit().putString(name, value).apply();
    }

    public void put(String name, Boolean value) {
        sp.edit().putBoolean(name, value).apply();
    }

    public void put(String name, long value) {
        sp.edit().putLong(name, value).apply();
    }

    public String getString(String name) {
        return sp.getString(name, null);
    }

    public long getLong(String name){
        return sp.getLong(name, 0);
    }

    public boolean getBoolean(String name) {
        return sp.getBoolean(name, false);
    }
}
