package com.example.kadesa.ui.pemerintah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kadesa.LembagaActivity;
import com.example.kadesa.ProfileLembagaActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemerintahFragment extends Fragment {

    private PemerintahViewModel pemerintahViewModel;

    Button btnLembaga;

    AppSession appSession;
    BaseApiService baseApiService = UtilsApi.getApiService();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pemerintah, container, false);

        appSession = new AppSession(getActivity());

        final ArrayList<Pemerintah> pemerintahs = new ArrayList<>();
        final ListView listView = view.findViewById(R.id.tv_listPemerintah);
        final PemerintahAdapter adapter = new PemerintahAdapter(getActivity(), pemerintahs);

        appSession = new AppSession(getActivity());

        baseApiService.getDataPemerintahan(appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.d("Pemerintah : " ,response.body().toString());
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i<jsonArray.length(); i++){
                            pemerintahs.add(new Pemerintah(jsonArray.getJSONObject(i).getString("name")));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            parent.getItemAtPosition(position);
                            Intent intent = new Intent(getActivity(), LembagaActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        return view;
    }
}