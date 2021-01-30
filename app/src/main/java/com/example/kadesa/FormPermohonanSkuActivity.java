package com.example.kadesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.AllDistrict;
import com.example.kadesa.model.ApiResponse;
import com.example.kadesa.model.Kabupaten;
import com.example.kadesa.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPermohonanSkuActivity extends AppCompatActivity {

    private Spinner spinnerJK, spinnerStatusPerkawinan, spinnerStatusBangunan;
    private int SKU = 1;
    final String[] listJK = {"Laki-laki", "Perempuan"};
    final String[] listStatusPerkawinan = {"Sudah Menikah","Belum Menikah", "Cerai Hidup", "Cerai Mati"};
    final String[] listStatusBangunan = {"hak milik","kontrak", "hak guna bangunan"};
    private String selectedJK;
    private int selectedStatusPerkawinan, selectedStatusBangunan;
    private EditText nama, ttl, pekerjaan, alamatAsal, perusahaan, bidangUsaha, tahunKegiatan, sppt, lokasiUsaha;
    private AutoCompleteTextView acAsalLetter;
    private Button btnSubmitLetter;
    private final BaseApiService baseApiService = UtilsApi.getApiService();
    AppSession appSession;
    private User user;
    private List<AllDistrict> mAllDistrict = new ArrayList<>();
    ArrayAdapter<AllDistrict> mAdapterAllDisctrict;
    private AllDistrict selectedAllDisctrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_permohonan_sku);

        appSession = new AppSession(this);
        //Log.d("session", appSession.getData(AppSession.USER));
        appSession.getData(AppSession.USER);
        user = new Gson().fromJson(appSession.getData(AppSession.USER), User.class);

        nama = (EditText) findViewById(R.id.et_namaLetter);
        nama.setText(user.getName());
        acAsalLetter = (AutoCompleteTextView) findViewById(R.id.ac_asalLetter);
        ttl = (EditText) findViewById(R.id.et_tanggalLahirLetter);
        pekerjaan = (EditText) findViewById(R.id.et_pekerjaanLetter);
        alamatAsal = (EditText) findViewById(R.id.et_alamatAsalLetter);
        perusahaan = (EditText) findViewById(R.id.et_perusahaanLetter);
        bidangUsaha = (EditText) findViewById(R.id.et_usahaLetter);
        tahunKegiatan = (EditText) findViewById(R.id.et_tahunKegiatanLetter);
        sppt = (EditText) findViewById(R.id.et_spptLetter);
        lokasiUsaha = (EditText) findViewById(R.id.et_lokasiUsahaLetter);
        btnSubmitLetter = (Button) findViewById(R.id.btn_submitLetter);

        Calendar newCalendar = Calendar.getInstance();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                ttl.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        ttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        btnSubmitLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitLetter();
            }
        });

        getSupportActionBar().setTitle("Permohonan SKU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinnerJK = findViewById(R.id.spinnerJK);
        spinnerStatusPerkawinan = findViewById(R.id.spinnerStatusPerkawinan);
        spinnerStatusBangunan = findViewById(R.id.spinner_buildingStatus);

        spinnerJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    selectedJK = "L";
                    Log.d("genderr" , selectedJK);
                }
                else if (position == 1){
                    selectedJK = "P";
                    Log.d("genderr" , selectedJK);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        spinnerStatusPerkawinan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    selectedStatusPerkawinan = 0;
                }else if(position == 1){
                    selectedStatusPerkawinan = 1;
                }else if(position == 2){
                    selectedStatusPerkawinan = 2;
                }else if(position == 3){
                    selectedStatusPerkawinan = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerStatusBangunan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    selectedStatusBangunan = 1;
                }else if (position == 1){
                    selectedStatusBangunan = 2;
                }else if (position == 2){
                    selectedStatusBangunan = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayAdapter<String> adapterStatusBangunan = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,listStatusBangunan);
        spinnerStatusBangunan.setAdapter(adapterStatusBangunan);

        final ArrayAdapter<String> adapterStatusPerkawinan = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,listStatusPerkawinan);
        spinnerStatusPerkawinan.setAdapter(adapterStatusPerkawinan);

        final ArrayAdapter<String> adapterJK = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,listJK);

        adapterJK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJK.setAdapter(adapterJK);



        baseApiService.getAllDistrict().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        ApiResponse status = new Gson().fromJson(result, ApiResponse.class);

                        if (status.getStatus().equalsIgnoreCase("success")){
                            JSONObject jsonObject = new JSONObject(result);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                mAllDistrict.add(new AllDistrict(jsonArray.getJSONObject(i).getInt("id"),
                                        jsonArray.getJSONObject(i).getString("name")));
                            }

                            mAdapterAllDisctrict = new ArrayAdapter<AllDistrict>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mAllDistrict);
                            acAsalLetter.setAdapter(mAdapterAllDisctrict);
                            acAsalLetter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    selectedAllDisctrict=(AllDistrict) parent.getAdapter().getItem(position);
                                }
                            });

                        }

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    public void SubmitLetter(){
        String mNama = user.getName();
        //String mAsal = acAsalLetter.getText().toString();
        int regAsal = selectedAllDisctrict.getId();
        String mTtl = ttl.getText().toString();
        String mPekerjaan = pekerjaan.getText().toString();
        int mStatusPerkawinan = selectedStatusPerkawinan;
        int mStatusBangunan = selectedStatusBangunan;
        String mAlamatAsal = alamatAsal.getText().toString();
        String mPerusahaan = perusahaan.getText().toString();
        String mBidangUsaha = bidangUsaha.getText().toString();
//        String mStatusBangunan = statusBangunan.getText().toString();
        int mTahunKegiatan = Integer.parseInt(tahunKegiatan.getText().toString());
        String mSppt = sppt.getText().toString();
        String mLokasiUsaha = lokasiUsaha.getText().toString();

        baseApiService.submitLetter(appSession.getData(AppSession.TOKEN), SKU, mNama, regAsal,selectedJK,
                mTtl, mStatusPerkawinan, mAlamatAsal, mPerusahaan,
                mBidangUsaha,mStatusBangunan,mTahunKegiatan, mSppt,
                mLokasiUsaha).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        ApiResponse status = new Gson().fromJson(result, ApiResponse.class);
                        if (status.getStatus().equalsIgnoreCase("success")){
                            Toast.makeText(getApplicationContext(),"Berhasil submit", Toast.LENGTH_SHORT);
                            finish();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Gagal submit", Toast.LENGTH_LONG);
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"gagal Load submit", Toast.LENGTH_LONG);
                return;
            }
        });

    }
}