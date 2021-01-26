package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ApiResponse;
import com.example.kadesa.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailArtikelActivity extends AppCompatActivity {

    AppSession appSession;
    User user;
    BaseApiService baseApiService = UtilsApi.getApiService();
    Picasso picasso;
    int id;

    private ImageView imgArtikel;
    private TextView judulArtikel, descArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        imgArtikel = (ImageView) findViewById(R.id.tv_imgArtikel);
        judulArtikel = (TextView) findViewById(R.id.tv_artikelJudul);
        descArtikel = (TextView) findViewById(R.id.tv_artikelIsi);
        Intent intent = getIntent();
        final int temp = intent.getIntExtra("id", 1);
        appSession = new AppSession(this);
        picasso = Picasso.get();

        id = getIntent().getIntExtra(Intent.EXTRA_EMAIL, 0);

        Log.d("DEBUG", "ID ARTIKEL : " + appSession.getData(AppSession.TOKEN));

        baseApiService.getDetailArtikel(id, appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        ApiResponse status = new Gson().fromJson(result, ApiResponse.class);
                        if (status.getStatus().equalsIgnoreCase("success")){
                            JSONObject jsonObject = new JSONObject(result);
                            JSONObject objectData = new JSONObject(jsonObject.getString("data"));
                            //Log.d("Succes" , jsonObject.getJSONObject("data").getString("name"));
                            judulArtikel.setText(objectData.getString("name"));
                            descArtikel.setText(objectData.getString("description"));
                            picasso.load(objectData.getString("image")).into(imgArtikel);
                        }else {
                            Toast.makeText(getApplicationContext(), "salah", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "salah", Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }
}