package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.adapter.PermohonanSkuAdapter;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ApiResponse;
import com.example.kadesa.model.PermohonanSku;
import com.example.kadesa.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermohonanSkuActivity extends AppCompatActivity {

    ListView listView;
    final ArrayList<PermohonanSku> permohonanSkuArrayList = new ArrayList<>();
    private PermohonanSkuAdapter permohonanSkuAdapter;
    private AppSession appSession;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        appSession = new AppSession(this);
        BaseApiService baseApiService = UtilsApi.getApiService();

        getSupportActionBar().setTitle("List Permohonan SKU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.listview);

        permohonanSkuAdapter = new PermohonanSkuAdapter(this, permohonanSkuArrayList);

        baseApiService.getListLetter(appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        ApiResponse status = new Gson().fromJson(result, ApiResponse.class);
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++){
                            permohonanSkuArrayList.add(new PermohonanSku(jsonArray.getJSONObject(i).getString("application_letter_type"),
                                    jsonArray.getJSONObject(i).getString("created_at"),
                                    jsonArray.getJSONObject(i).getString("application_status"),
                                    jsonArray.getJSONObject(i).getInt("id")));
                        }

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    listView.setAdapter(permohonanSkuAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                            adapter.getItemAtPosition(position);
                            Intent intent = new Intent(getApplicationContext(), DetailSkuActivity.class);
                            intent.putExtra(Intent.EXTRA_EMAIL, permohonanSkuArrayList.get(position).getmIdSurat());
                            //Log.d("id : ", permohonanSkuArrayList.get(position).getmIdSurat());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


//        permohonanSkuArrayList.add(new PermohonanSku("Formulir kartu keluarga (Pengganti F.1-01)",
//                "28 November 2020", "ID : 1301188595", "Terkirim"));


    }
}