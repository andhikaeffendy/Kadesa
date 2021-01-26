package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.DetailDesa;
import com.example.kadesa.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDesaActivity extends AppCompatActivity {

    private TextView tvNama, tvLat, tvLong, tvDeskripsi, tvEmail, tvPhoneNumber;
    private ImageView imvImage;
    private ImageButton btnAmbulanceCall;
    private AppSession appSession;
    private User user;
    private DetailDesa detailDesa;
    private final BaseApiService baseApiService = UtilsApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_desa);

        appSession = new AppSession(getApplicationContext());

        tvNama = (TextView) findViewById(R.id.tvName);
        tvLat = (TextView) findViewById(R.id.tvLat);
        tvLong = (TextView) findViewById(R.id.tvLong);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        tvEmail = (TextView) findViewById(R.id.tvEmailProfileDesa);
        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumberProfileDesa);
        imvImage = (ImageView) findViewById(R.id.imvImage);
        btnAmbulanceCall = (ImageButton) findViewById(R.id.btn_ambulanceCall);

        if (appSession.isLogin()){
            user = getUserSession();
            baseApiService.getDetailDesa(user.getVillageId()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        final JSONObject jsonData = new JSONObject(jsonObject.getString("data"));
                        tvNama.setText("Nama: "+jsonData.getString("name"));
                        tvDeskripsi.setText("Deskripsi: "+jsonData.getString("description"));
                        tvEmail.setText("Email: "+jsonData.getString("email"));
                        tvPhoneNumber.setText("Phone Number: "+jsonData.getString("phone_number"));
                        tvLat.setText("Lat: "+jsonData.getString("lat" + ""));
                        tvLong.setText("Long: "+jsonData.getString("long"+""));
                        final String url = jsonData.getString("ambulance_call");
                        btnAmbulanceCall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);
                            }
                        });
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Masih belum logout", Toast.LENGTH_SHORT);
                    return;
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),"Anda Harus login", Toast.LENGTH_SHORT);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

    private User getUserSession(){
        return new Gson().fromJson(appSession.getData(AppSession.USER), User.class);
    }

}