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
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class IndahDesakuActivity extends AppCompatActivity {

    ListView listView;
    String[] mNama = {"Doyok", "Kasino S.E", "Indro S.E"};
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
                Intent intent = new Intent(getApplicationContext(), IndahDesakuDatailActivity.class);
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
            View view = getLayoutInflater().inflate(R.layout.activity_indah_desaku,null);

            TextView mNamaVideo = view.findViewById(R.id.et_namaDesa);
            ImageView mImgVideo = view.findViewById(R.id.et_imgDesa);

            mNamaVideo.setText(mNama[position]);
            mImgVideo.setImageResource(mImg[position]);


            return view;
        }
    }

}