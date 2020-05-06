package com.thesevenitsolutions.silentme;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class prefrence {
    private static prefrence preferences;
    private SharedPreferences.Editor writer;
    private Context ctx;
    private static final String SHARED_PREF_NAME = "sharedprefrence";
    private static final String START_TIME = "12:00";
    private static final String END_TIME = "18:00";
    private prefrence(Context context) {
        ctx = context;
    }

    public static synchronized prefrence getInstance(Context context) {
        if (preferences == null) {
            preferences = new prefrence(context);
        }
        return preferences;
    }
    public boolean setStartTime(silent silent){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

    if (sharedPreferences.getString(END_TIME,null)!=null && sharedPreferences.getString(START_TIME,null)!=null){
        return true;
    }
     return false;
    }

    public long getStartTime() {
        silent silent=new silent();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return Long.parseLong(sharedPreferences.getString(START_TIME,null));
    }

    public  long getEndTime() {
        silent silent=new silent();
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        return Long.parseLong(sharedPreferences.getString(END_TIME,null));
    }
}
