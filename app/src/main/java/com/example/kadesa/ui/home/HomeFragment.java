package com.example.kadesa.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.kadesa.IndahDesakuActivity;
import com.example.kadesa.LembagaActivity;
import com.example.kadesa.ProfileDesaActivity;
import com.example.kadesa.R;
import com.example.kadesa.VideoActivity;
import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.adapter.ArtikelTerbaruAdapter;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.RetrofitClient;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ArtikelTerbaru;
import com.example.kadesa.model.Slider;
import com.example.kadesa.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private User user;

    private List<ArtikelTerbaru> artikelTerbaruList;
    AppSession appSession;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container,false);

        final ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        imageSlider.setImageList(slideModels,true);

        recyclerView = (RecyclerView) view.findViewById(R.id.tv_recycleArtikelHome);

        layoutManager = new LinearLayoutManager(getActivity());
        appSession = new AppSession(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        artikelTerbaruList = new ArrayList<>();


        //artikelTerbaruList.add(new ArtikelTerbaru(R.drawable.ic_video,"Judul","Deskripsiii"));



        Button btnProfileDesa = (Button) view.findViewById(R.id.btn_profileDesa);
        Button btnAmbulance = (Button) view.findViewById(R.id.btn_ambulance);
        Button btnIndahDesaku = (Button) view.findViewById(R.id.btn_indahDesaku);
        Button btnVideo = (Button) view.findViewById(R.id.btn_video);


         btnProfileDesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDesaActivity.class);
                startActivity(intent);
            }
        });

        btnAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone=6282198113362";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btnIndahDesaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IndahDesakuActivity.class);
                startActivity(intent);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent);
            }
        });

        BaseApiService baseApiService = UtilsApi.getApiService();

        if (appSession.isLogin()){
            baseApiService.getSliderAfterLogin(1,appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        List<SlideModel> slideModels = new ArrayList<>();
                        Log.d("Udah Login : ",response.body().toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i<jsonArray.length(); i++){
//                            Log.d("Json Array ke -", "" + jsonArray.getJSONObject(i).getString("image"));
                                slideModels.add(new SlideModel(jsonArray.getJSONObject(i).getString("image"),jsonArray.getJSONObject(i).getString("name")));
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        imageSlider.setImageList(slideModels,true);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } else {
            baseApiService.getSliderBeforeLogin(1).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        List<SlideModel> slideModels = new ArrayList<>();
                        Log.d("Belum Login : ",response.body().toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i<jsonArray.length(); i++){
//                            Log.d("Json Array ke -", "" + jsonArray.getJSONObject(i).getString("image"));
                                slideModels.add(new SlideModel(jsonArray.getJSONObject(i).getString("image"),jsonArray.getJSONObject(i).getString("name")));
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        imageSlider.setImageList(slideModels,true);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        if (appSession.isLogin()){
            baseApiService.getArtikelAfterLogin(3,appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Log.d("Artikel Login : ",response.body().toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i<jsonArray.length(); i++){
                                //Log.d("Json Array ke -", "" + jsonArray.getJSONObject(i).getString("image"));
                                artikelTerbaruList.add(new ArtikelTerbaru(jsonArray.getJSONObject(i).getInt("id"), jsonArray.getJSONObject(i).getString("image"),
                                        jsonArray.getJSONObject(i).getString("name"),"Deskripsiii"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mAdapter = new ArtikelTerbaruAdapter(getActivity(), artikelTerbaruList);
                        recyclerView.setAdapter(mAdapter);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }else {
            baseApiService.getArtikelBeforeLogin(3).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Log.d("Artikel Belum : ",response.body().toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i<jsonArray.length(); i++){
                                //Log.d("Json Array ke -", "" + jsonArray.getJSONObject(i).getString("image"));
                                artikelTerbaruList.add(new ArtikelTerbaru(jsonArray.getJSONObject(i).getInt("id"), jsonArray.getJSONObject(i).getString("image"),
                                        jsonArray.getJSONObject(i).getString("name"),"Deskripsiii"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mAdapter = new ArtikelTerbaruAdapter(getActivity(), artikelTerbaruList);

                        recyclerView.setAdapter(mAdapter);

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }

        return view;
    }
}