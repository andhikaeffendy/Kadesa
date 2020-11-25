package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryPermohonanSkuActivity extends AppCompatActivity {

    ListView listView;
    String[] mNama = {"Doyok", "Kasino S.E", "Indro S.E"};
    String[] mTanggal = {"jumat, 12 desember 2020", "jumat, 12 desember 2020", "jumat, 12 desember 2020"};
    int[] mImg = {R.drawable.lembaga_desa, R.drawable.lembaga_eksekutif, R.drawable.ic_baseline_assignment_24};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        listView = (ListView)findViewById(R.id.listview);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailSkuActivity.class);
                startActivity(intent);
            }
        });

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mImg.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.activity_history_permohonan_sku,null);

            TextView mNamaListKK = view.findViewById(R.id.et_namaSKU);
            TextView mTglKK = view.findViewById(R.id.et_tglSKU);
            ImageView mImgVideo = view.findViewById(R.id.et_imgSKU);

            mNamaListKK.setText(mNama[position]);
            mTglKK.setText(mTanggal[position]);
            mImgVideo.setImageResource(mImg[position]);


            return view;
        }
    }
}