package com.example.kadesa.helper;

import android.content.SharedPreferences;

import com.example.kadesa.model.User;
import com.google.gson.Gson;

public class AppSession {

    public final static String SHAREDKEY = "Kadesa-session";
    public final static String TOKEN = "token";
    public final static String USER = "username";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public void clearSession() {
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }

    public void logout(){
        editor = sharedPreferences.edit();
        editor.remove(TOKEN);
        editor.remove(USER);
        editor.clear();
        editor.apply();
    }

    public String getData(String data){
        return sharedPreferences.getString(data, null);
    }

    public String getData(String data, String default_value){
        return sharedPreferences.getString(data, default_value);
    }

    public void setData(String data, String value){
        editor = sharedPreferences.edit();
        editor.putString(data, value);
        editor.apply();
    }

    public void deleteData(String data){
        editor = sharedPreferences.edit();
        editor.remove(data);
        editor.apply();
    }

    public void createSession(String token, User user){
        editor = sharedPreferences.edit();
        editor.putString(TOKEN, token);
        editor.putString(USER, new Gson().toJson(user));
        editor.apply();
    }

}
