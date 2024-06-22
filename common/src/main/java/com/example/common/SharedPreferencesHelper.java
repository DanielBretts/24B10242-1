package com.example.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {

    private final SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "LibraryMatches";

    private final String KEY = "matches";
    private final Gson gson;
    public SharedPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    // Save an object
    public void saveMatch(Match object) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<Match> matches = getMatches();
        if (matches == null) {
            matches = new ArrayList<Match>();
        }

        matches.add(object);
        String json = gson.toJson(matches);
        editor.putString(KEY, json);
        editor.apply();
    }


    public List<Match> getMatches() {
        String json = sharedPreferences.getString(KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Match>>() {}.getType();
        return gson.fromJson(json, type);
    }


}
