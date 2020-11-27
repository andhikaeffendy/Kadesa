package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KodeAktivasiActivity extends AppCompatActivity {

    private EditText mAktifasi;
    private String mEmail;
    private final BaseApiService baseApiService = UtilsApi.getApiService();
    private Button mSubmitCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_aktivasi);

        mAktifasi = (EditText) findViewById(R.id.tv_aktifasiKode);
        mSubmitCode = (Button) findViewById(R.id.btn_submitKode);

        mSubmitCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AktivationSubmit();
            }
        });



    }

    public void AktivationSubmit(){
        String mKodeAktifasi = mAktifasi.getText().toString();
        mEmail = getIntent().getStringExtra(Intent.EXTRA_EMAIL);

        baseApiService.activationCodeSubmit(mEmail,mKodeAktifasi).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equals("success")){
                            Intent intent = new Intent(getApplicationContext(), RegisterSuccesActivity.class);
                            startActivity(intent);
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

            }
        });
    }

}