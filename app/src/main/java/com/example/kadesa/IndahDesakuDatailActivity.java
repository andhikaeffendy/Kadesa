package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class IndahDesakuDatailActivity extends AppCompatActivity {

    private TextView tvJudulDetail, tvDescDetail;
    private ImageView imgDesaku;
    BaseApiService mApiService;
    AppSession appSession;
    Picasso picasso;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indah_desaku_datail);

        tvJudulDetail = (TextView) findViewById(R.id.desaku_judul);
        tvDescDetail = (TextView) findViewById(R.id.desaku_isi);
        imgDesaku = (ImageView) findViewById(R.id.desaku_foto);

        appSession = new AppSession(this);
        mApiService = UtilsApi.getApiService();
        picasso = Picasso.get();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("desaku_id");
        } else {
            id = 0;
        }

        mApiService.getDetailDesaku(id, appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONObject objectData = new JSONObject(jsonObject.getString("data"));
                        Log.d("Succes" , jsonObject.getJSONObject("data").getString("name"));
                        tvJudulDetail.setText(objectData.getString("name"));
                        tvDescDetail.setText(objectData.getString("description"));
                        picasso.load(objectData.getString("image")).into(imgDesaku);
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