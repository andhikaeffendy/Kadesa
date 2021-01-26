package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;

public class ResetPasswordActivity extends AppCompatActivity {

    private final BaseApiService baseApiService = UtilsApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

    }
}