package com.example.user.liangzi.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import retrofit2.http.PUT;

/**
 * Created by User on 2017/4/17.
 */

public class SharedPUtils {
    private static final String sharedPreferencesInfo = "local_private_pre";

    private static Context myContext;
    private static SharedPreferences saveInfo;
    private static SharedPreferences.Editor saveEditor;
    private static volatile SharedPUtils sharedPreferencesUtil ;

    private SharedPUtils(Context context) {
        myContext = context;
        if (saveInfo == null && myContext != null) {
            saveInfo = myContext.getSharedPreferences(sharedPreferencesInfo, Context.MODE_PRIVATE);
            saveEditor = saveInfo.edit();
        }
    }


    public static SharedPUtils getInstance(Context context) {
        if(sharedPreferencesUtil == null) {
            synchronized (SharedPUtils.class) {
                if(sharedPreferencesUtil == null) {
                    sharedPreferencesUtil = new SharedPUtils(context);
                }
            }
        }
        return sharedPreferencesUtil;
    }


    public boolean isContainKey(String key) {
        return saveInfo.contains(key);
    }

    public String getString(String key) {
        return saveInfo.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return saveInfo.getString(key, defaultValue);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, String> getAll() {
        return (HashMap<String, String>) saveInfo.getAll();
    }



    public  boolean getBoolean(String key, boolean defaultValue) {
        return saveInfo.getBoolean(key, defaultValue);
    }

    public  boolean setBoolean(String key, boolean value) {
        if (saveInfo.contains(key)) {
            saveEditor.remove(key);
        }
        saveEditor.putBoolean(key, value);
        return saveEditor.commit();
    }

    public boolean setString(String key, String value) {
        if (saveInfo.contains(key)) {
            saveEditor.remove(key);
        }
        saveEditor.putString(key, value);
        return saveEditor.commit();
    }

    public boolean clearItem(String key) {
        saveEditor.remove(key);
        return saveEditor.commit();
    }
}
