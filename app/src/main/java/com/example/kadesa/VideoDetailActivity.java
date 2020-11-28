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

public class VideoDetailActivity extends AppCompatActivity {

    private TextView tvJudulVideo, tvDescVideo;
    private ImageView imgDetailVideo;
    BaseApiService mApiService;
    AppSession appSession;
    Picasso picasso;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        tvJudulVideo = (TextView) findViewById(R.id.video_judul);
        tvDescVideo = (TextView) findViewById(R.id.videoDetailDesc);
        imgDetailVideo = (ImageView) findViewById(R.id.imgDetailVideo);

        appSession = new AppSession(this);
        mApiService = UtilsApi.getApiService();
        picasso = Picasso.get();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("video_id");
        } else {
            id = 0;
        }

        mApiService.getDetailVideo(id, appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONObject objectData = new JSONObject(jsonObject.getString("data"));
                        tvJudulVideo.setText(objectData.getString("name"));
                        tvDescVideo.setText(objectData.getString("description"));
                        picasso.load(objectData.getString("image")).into(imgDetailVideo);
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