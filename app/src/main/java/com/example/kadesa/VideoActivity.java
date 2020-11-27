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

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.adapter.VideoAdapter;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.User;
import com.example.kadesa.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {

    BaseApiService baseApiService = UtilsApi.getApiService();
    AppSession appSession;
    User user;
    ListView listView;
    final ArrayList<Video> videoArrayList = new ArrayList<>();
    VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        listView = (ListView)findViewById(R.id.listview);

        appSession = new AppSession(getApplicationContext());

        videoAdapter = new VideoAdapter(this, videoArrayList);

        if (appSession.isLogin()){
            baseApiService.getListVideo(appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i<jsonArray.length(); i++){
                                videoArrayList.add(new Video(jsonArray.getJSONObject(i).getString("image"),
                                        jsonArray.getJSONObject(i).getString("name")));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        listView.setAdapter(videoAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), VideoDetailActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

    }

}