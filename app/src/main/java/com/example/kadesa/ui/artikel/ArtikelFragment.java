package com.example.kadesa.ui.artikel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kadesa.DetailArtikelActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ArtikelTerbaru;
import com.example.kadesa.model.User;
import com.example.kadesa.ui.artikel.ArtikelViewModel;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelFragment extends Fragment {

    AppSession appSession;
    User user;
    Artikel artikel;

    private ArtikelViewModel artikelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.list_artikel, container,false);

        appSession = new AppSession(getActivity());

        final ArrayList<Artikel> artikels = new ArrayList<>();

        final ListView list = view.findViewById(R.id.list_artikel);

        final ArtikelAdapter adapter = new ArtikelAdapter(getActivity(), artikels);

        BaseApiService baseApiService = UtilsApi.getApiService();

        if (appSession.isLogin()){
            Log.d("DEBUG", "TOKEN " + AppSession.TOKEN);
            baseApiService.getListArtikelAfterLogin(appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Log.d("ListArtikel_login : ",appSession.getData(AppSession.TOKEN));
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++){
                                artikels.add(new Artikel(jsonArray.getJSONObject(i).getString("image"),
                                        jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getInt("id")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                                adapter.getItemAtPosition(position);
                                Log.d("ID ", "ARTIKEL " + artikels.get(position).getId() + " Name : " +artikels.get(position).getmNamaArtikel() );
                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
                                intent.putExtra(Intent.EXTRA_EMAIL, artikels.get(position).getId());
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
            baseApiService.getListArtikel().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Log.d("ListArtikel : ",response.body().toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++){

                                artikels.add(new Artikel(jsonArray.getJSONObject(i).getString("image"),
                                        jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getInt("id")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        list.setAdapter(adapter);
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                                adapter.getItemAtPosition(position);
                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
                                intent.putExtra("artikel_id", id);
                                startActivity(intent);
                            }
                        });
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
