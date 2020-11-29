package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfileLembagaActivity extends AppCompatActivity {

    BaseApiService mApiService;
    AppSession appSession;
    int id;
    Picasso picasso;

    private TextView tvName, tvDesc;
    private ImageView imgDetailLembaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_lembaga);

        mApiService = UtilsApi.getApiService();
        appSession = new AppSession(this);
        picasso = Picasso.get();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("member_id");
        } else {
            id = 0;
        }

        tvName = (TextView) findViewById(R.id.et_tvNamaLembaga);
        tvDesc = (TextView) findViewById(R.id.desc_detailLembaga);
        imgDetailLembaga = (ImageView) findViewById(R.id.imgLembaga);

        getDetailMember();
    }

    private void getDetailMember() {
        mApiService.getDetailMemberLembaga(id, appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        JSONObject data = object.getJSONObject("data");
                        tvName.setText(data.getString("name"));
                        tvDesc.setText(data.getString("description"));
                        if (!data.getString("photo").equals("")) {
                            picasso.load(data.getString("photo")).into(imgDetailLembaga);
                        }
                    } catch (JSONException | IOException e) {
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