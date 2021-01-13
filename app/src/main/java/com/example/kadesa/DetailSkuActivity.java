package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailSkuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sku);

        getSupportActionBar().setTitle("Detail Permohonan SKU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}