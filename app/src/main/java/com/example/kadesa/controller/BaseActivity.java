package com.example.kadesa.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kadesa.MainActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;

public class BaseActivity extends AppCompatActivity {

    public final static String TAG = "Kadesa App";

    public AppSession appSession;
    public Context context;
    public BaseApiService mApiService;
    private Dialog progressDialog;

    protected void initViews(){
        context = this;
        appSession = new AppSession(this);
        mApiService = UtilsApi.getApiService();
        progressDialog = new Dialog(context);
    }

    public boolean checkSession(){
        if(!appSession.isLogin()) {
            Intent i = new Intent(context, MainActivity.class);
            startActivity(i);
            finish();
            return false;
        }
        return true;
    }

    public void checkSession(Context context){
        if(!appSession.isLogin()) {
            Intent i = new Intent(context, MainActivity.class);
            startActivity(i);
            finish();
        }
    }



//    public void logout(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context)
//                .setTitle(R.string.logout)
//                .setMessage(R.string.warning_logout);
//        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked OK button
//                showProgressBar(true);
//                mApiService.(appSession.getData(AppSession.TOKEN))
//                        .enqueue(logoutCallback.getCallback());
//            }
//        });
//
//        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User clicked OK button
//
//            }
//        });
//
//
//        AlertDialog alertDialog = builder.create();
//
//        alertDialog.show();
//    }
//
//    public void showProgressBar(boolean show){
//        if(show){
//            showProgres();
//        } else {
//            progressDialog.dismiss();
//        }
//    }

}
