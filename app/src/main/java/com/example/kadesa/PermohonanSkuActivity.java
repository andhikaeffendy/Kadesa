package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kadesa.helper.adapter.PermohonanSkuAdapter;
import com.example.kadesa.model.PermohonanSku;

import java.util.ArrayList;

public class PermohonanSkuActivity extends AppCompatActivity {

    ListView listView;
    final ArrayList<PermohonanSku> permohonanSkuArrayList = new ArrayList<>();
    PermohonanSkuAdapter permohonanSkuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        getSupportActionBar().setTitle("List Permohonan SKU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.listview);

        permohonanSkuAdapter = new PermohonanSkuAdapter(this, permohonanSkuArrayList);
        permohonanSkuArrayList.add(new PermohonanSku("Nomor Surat : 503/ .. / XI/Desa/2020", "Wiradipa", "Android"));
        permohonanSkuArrayList.add(new PermohonanSku("Nomor Surat : 503/ .. / XI/Desa/2020", "Nusantara", "Website"));

        listView.setAdapter(permohonanSkuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailSkuActivity.class);
                startActivity(intent);
            }
        });
    }
}