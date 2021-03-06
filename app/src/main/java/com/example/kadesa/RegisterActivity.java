package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.Desa;
import com.example.kadesa.model.Kabupaten;
import com.example.kadesa.model.Kecamatan;
import com.example.kadesa.model.Pekerjaan;
import com.example.kadesa.model.Provinsi;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

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

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView btnMasukLogin;
    EditText namaReg, emailReg, passReg, confirmPassReg, alamatReg, noTelpReg;
    Spinner spinnerJK;
    final String[] mJK = { "Laki-Laki", "Perempuan"};
    private String mJenisKelamin;
    private List<Provinsi> mProvinsi = new ArrayList<>();
    private List<Kabupaten> mKabupaten = new ArrayList<>();
    private List<Kecamatan> mKecamatan = new ArrayList<>();
    private List<Desa> mDesa = new ArrayList<>();
    private List<Pekerjaan> mPekerjaan = new ArrayList<>();
    ArrayAdapter<Provinsi> mAdapterProvinsi;
    ArrayAdapter<Kabupaten> mAdapterKabupaten;
    ArrayAdapter<Kecamatan> mAdapterKecamatan;
    ArrayAdapter<Desa> mAdapterDesa;
    ArrayAdapter<Pekerjaan> mAdapterPekerjaan;
    private AutoCompleteTextView autoProvinsi, autoKabupaten, autoKecamatan, autoDesa, autoPekerjaan;
    private Provinsi selectedProvinsi;
    private Kabupaten selectedKabupaten;
    private Kecamatan selectedKecamatan;
    private Desa selectedDesa;
    private Pekerjaan selectedPekerjaan;
    private final BaseApiService baseApiService = UtilsApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnMasukLogin = (TextView) findViewById(R.id.btn_goToLogin);
        spinnerJK = (Spinner) findViewById(R.id.tv_spinnerJK);
        autoProvinsi = (AutoCompleteTextView) findViewById(R.id.tv_provinsiRegister);
        autoKabupaten = (AutoCompleteTextView) findViewById(R.id.tv_kotaRegister);
        autoKecamatan = (AutoCompleteTextView) findViewById(R.id.tv_kecamatanRegister);
        autoPekerjaan = (AutoCompleteTextView) findViewById(R.id.tv_pekerjaanReg);
        autoDesa = (AutoCompleteTextView) findViewById(R.id.tv_desaRegister);

        namaReg = (EditText) findViewById(R.id.tv_namaRegister);
        emailReg = (EditText) findViewById(R.id.tv_emailRegister);
        passReg = (EditText) findViewById(R.id.tv_passwordRegister);
        confirmPassReg = (EditText) findViewById(R.id.tv_confirmPasswordRegister);
        alamatReg = (EditText) findViewById(R.id.tv_alamatRegister);
        noTelpReg = (EditText) findViewById(R.id.tv_noTelpReg);

        spinnerJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    mJenisKelamin = "L";
                }else {
                    mJenisKelamin = "P";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,mJK);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJK.setAdapter(adapter);



        baseApiService.getListProvinsi().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //Log.d("Provinsi : ",response.body().toString());
                    try {

                        JSONObject jsonObject = new JSONObject(response.body().string());
                        final JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++){
                            mProvinsi.add(new Provinsi(jsonArray.getJSONObject(i).getInt("id"),
                                    jsonArray.getJSONObject(i).getString("name")));
                        }
//
//                        Log.d("Provinsi : ","" + mProvinsi);
                        mAdapterProvinsi = new ArrayAdapter<Provinsi>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, mProvinsi);
                        autoProvinsi.setAdapter(mAdapterProvinsi);
                        autoProvinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

                                selectedProvinsi = (Provinsi) parent.getAdapter().getItem(position);
                                baseApiService.getListKabupaten(selectedProvinsi.getId()).enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        try {

                                            JSONObject jsonObject1 = new JSONObject(response.body().string());
                                            JSONArray jsonArray1 = jsonObject1.getJSONArray("data");

                                            for (int j = 0; j<jsonArray1.length(); j++){
                                                mKabupaten.add(new Kabupaten(jsonArray1.getJSONObject(j).getInt("id"),
                                                        jsonArray1.getJSONObject(j).getString("name")));
                                            }

                                            mAdapterKabupaten = new ArrayAdapter<Kabupaten>(getApplicationContext(),
                                                    android.R.layout.simple_spinner_dropdown_item, mKabupaten);
                                            autoKabupaten.setAdapter(mAdapterKabupaten);

                                            autoKabupaten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                    selectedKabupaten = (Kabupaten) parent.getAdapter().getItem(position);
                                                    baseApiService.getListKecamatan(selectedKabupaten.getId()).enqueue(new Callback<ResponseBody>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                            try {

                                                                Log.d("Kecamatan : ","" + mKecamatan);


                                                                JSONObject jsonObject2 = new JSONObject(response.body().string());
                                                                JSONArray jsonArray2 = jsonObject2.getJSONArray("data");

                                                                for (int k = 0; k<jsonArray2.length(); k++){
                                                                    mKecamatan.add(new Kecamatan(jsonArray2.getJSONObject(k).getInt("id"),
                                                                            jsonArray2.getJSONObject(k).getString("name")));
                                                                }

                                                                mAdapterKecamatan = new ArrayAdapter<Kecamatan>(getApplicationContext(),
                                                                        android.R.layout.simple_spinner_dropdown_item, mKecamatan);
                                                                autoKecamatan.setAdapter(mAdapterKecamatan);
                                                                autoKecamatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                        selectedKecamatan = (Kecamatan) parent.getAdapter().getItem(position);

                                                                        baseApiService.getListDesa(selectedKecamatan.getId()).enqueue(new Callback<ResponseBody>() {
                                                                            @Override
                                                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                                                try {

                                                                                    JSONObject jsonObject3 = new JSONObject(response.body().string());
                                                                                    JSONArray jsonArray3 = jsonObject3.getJSONArray("data");

                                                                                    for (int i = 0; i<jsonArray3.length(); i++){
                                                                                        mDesa.add(new Desa(jsonArray3.getJSONObject(i).getInt("id"),
                                                                                                jsonArray3.getJSONObject(i).getString("name")));
                                                                                    }

                                                                                    mAdapterDesa = new ArrayAdapter<Desa>(getApplicationContext(),
                                                                                            android.R.layout.simple_spinner_dropdown_item, mDesa);
                                                                                    autoDesa.setAdapter(mAdapterDesa);
                                                                                    autoDesa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                                        @Override
                                                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                                            selectedDesa = (Desa) parent.getAdapter().getItem(position);
                                                                                        }
                                                                                    });

                                                                                } catch (JSONException e) {
                                                                                    e.printStackTrace();
                                                                                } catch (IOException e) {
                                                                                    e.printStackTrace();
                                                                                }
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                                                                            }
                                                                        });
                                                                    }
                                                                });

                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                                                        }
                                                    });
                                                }
                                            });

                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });
                            }
                        });

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

        baseApiService.getListPekerjaan().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.d("Pekerjaan : ","" + mPekerjaan);
                    try {

                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++){
                            mPekerjaan.add(new Pekerjaan(jsonArray.getJSONObject(i).getInt("id"),
                                    jsonArray.getJSONObject(i).getString("name")));
                        }
//
                        mAdapterPekerjaan = new ArrayAdapter<Pekerjaan>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, mPekerjaan);
                        autoPekerjaan.setAdapter(mAdapterPekerjaan);
                        autoPekerjaan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                selectedPekerjaan=(Pekerjaan) parent.getAdapter().getItem(position);
                            }
                        });

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedProvinsi == null){
                    autoProvinsi.setError(getString(R.string.error_provinsi));
                    return;
                }

                if (selectedKecamatan == null){
                    autoProvinsi.setError(getString(R.string.error_kecamatan));
                    return;
                }

                if (selectedKabupaten == null){
                    autoProvinsi.setError(getString(R.string.error_kabupaten));
                    return;
                }

                if (selectedDesa == null){
                    autoProvinsi.setError(getString(R.string.error_desa));
                    return;
                }

                if (selectedPekerjaan == null){
                    autoProvinsi.setError(getString(R.string.error_pekerjaan));
                    return;
                }
                SignUpNow();
            }
        });

        btnMasukLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    public void SignUpNow(){

        String mNama = namaReg.getText().toString();
        final String mEmail = emailReg.getText().toString();
        String mPass = passReg.getText().toString();
        String mConfirmPass = confirmPassReg.getText().toString();
        String mAlamat = alamatReg.getText().toString();
        int regProvinsi = selectedProvinsi.getId();
        int regKabupaten = selectedKabupaten.getId();
        int regKecamatan = selectedKecamatan.getId();
        int mDesa = selectedDesa.getId();
        String mNoTelp = noTelpReg.getText().toString();
        int mPekerjaan = selectedPekerjaan.getId();

        baseApiService.registerRequest(mEmail,mPass,mConfirmPass,mNama,mAlamat,regProvinsi,regKabupaten,regKecamatan,
                mDesa,mPekerjaan,mJenisKelamin,mNoTelp).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("status").equals("success")){
                            Intent intent = new Intent(getApplicationContext(), KodeAktivasiActivity.class);
                            //intent.putExtra("email ", emailReg.getText().toString());
                            intent.putExtra(Intent.EXTRA_EMAIL, mEmail);
                            startActivity(intent);
                            finish();
                        }
                        if (object.getString("status").equals("fail")){
                            Toast.makeText(getApplication(), object.getString("message"), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{

                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, response.message(), Snackbar.LENGTH_LONG);
                            return;
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, response.message(), Snackbar.LENGTH_LONG);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, t.getMessage(), Snackbar.LENGTH_LONG);
            }
        });
    }
}