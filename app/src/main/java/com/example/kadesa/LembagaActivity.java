package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.adapter.LembagaAdapter;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.MemberPemerintah;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LembagaActivity extends AppCompatActivity {

    TextView tvNameLembaga, tvDescLembaga;
    ListView listView;
    String[] mNama = {"Doyok", "Kasino S.E", "Indro S.E"};
    String[] mDesc = {"Warkop Reborn", "Warkop Reborn", "Warkop Reborn"};
    int[] mImg = {R.drawable.lembaga_desa, R.drawable.lembaga_eksekutif, R.drawable.ic_baseline_assignment_24};
    List<MemberPemerintah> dataMembers = new ArrayList<MemberPemerintah>();
    LembagaAdapter adapter;

    BaseApiService mApiService;
    AppSession appSession;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembaga);

        tvNameLembaga = (TextView) findViewById(R.id.tv_namaLembagaDesa);
        tvDescLembaga = (TextView) findViewById(R.id.tv_deskripsiLembagaDesa);
        listView = (ListView) findViewById(R.id.et_listAnggotaLembaga);

        mApiService = UtilsApi.getApiService();
        appSession = new AppSession(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("lembaga_id");
        } else {
            id = 0;
        }

        getLembaga();
    }

    private void getLembaga() {
        mApiService.getLembaga(id, appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject.getJSONArray("members");
                        tvNameLembaga.setText(data.getString("name"));
                        tvDescLembaga.setText(data.getString("description"));

                        for (int i=0; i<jsonArray.length(); i++) {
                            dataMembers.add(new MemberPemerintah(jsonArray.getJSONObject(i).getInt("id"), jsonArray.getJSONObject(i).getString("name"),
                                    jsonArray.getJSONObject(i).getString("description"), jsonArray.getJSONObject(i).getString("photo")));
                        }
                        adapter = new LembagaAdapter(getApplicationContext(), dataMembers);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(view.getContext(), "ID : " + dataMembers.get(position).getId(), Toast.LENGTH_SHORT);
                                Intent intent = new Intent(getApplicationContext(), ProfileLembagaActivity.class);
                                intent.putExtra("member_id", dataMembers.get(position).getId());
                                startActivity(intent);
                                finish();
                            }
                        });
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

//    private class CustomAdapter extends BaseAdapter {
//        @Override
//        public int getCount() {
//            return mImg.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = getLayoutInflater().inflate(R.layout.activity_lembaga,null);
//
//            TextView mNamaLembaga = view.findViewById(R.id.et_namaLembaga);
//            TextView mJabatan = view.findViewById(R.id.et_jabatanLembaga);
//            ImageView mImgLembaga = view.findViewById(R.id.et_imgLembaga);
//
//            mNamaLembaga.setText(mNama[position]);
//            mImgLembaga.setImageResource(mImg[position]);
//
//
//            return view;
//        }
//    }

}