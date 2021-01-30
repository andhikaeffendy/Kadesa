package com.example.kadesa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ApiResponse;
import com.example.kadesa.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSkuActivity extends AppCompatActivity {

    private AppSession appSession;
    private User user;
    private BaseApiService baseApiService = UtilsApi.getApiService();
    private int id;
    private TextView tvName, tvTempat, tvTanggalLahir, tvJk, tvStatusPerkawinan, tvPekerjaan, tvAlamatAsal,
            tvNamaPerusahaan, tvBidangUsaha, tvStatusBangunan, tvTahunKegiatan, tvNoSppt, tvLokasiUsaha, tvTitleSurat, tvNomorSurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sku);

        getSupportActionBar().setTitle("Detail Permohonan SKU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvTitleSurat = findViewById(R.id.tv_titleSurat);
        tvNomorSurat = findViewById(R.id.tv_nomorSurat);
        tvName = findViewById(R.id.tv_namaSku);
        tvTempat = findViewById(R.id.tv_tempatSku);
        tvTanggalLahir = findViewById(R.id.tv_tanggalLahirSku);
        tvJk = findViewById(R.id.tv_jKSku);
        tvStatusPerkawinan = findViewById(R.id.tv_statusPerkawinanSku);
        tvAlamatAsal = findViewById(R.id.tv_alamatAsalSku);
        //tvPekerjaan = findViewById(R.id.tv_pekerjaanSku);
        tvNamaPerusahaan = findViewById(R.id.tv_namaPerusahaan);
        tvBidangUsaha = findViewById(R.id.tv_bidangUsaha);
        tvStatusBangunan = findViewById(R.id.tv_statusBangunan);
        tvTahunKegiatan = findViewById(R.id.tv_tahunKegiatan);
        tvNoSppt = findViewById(R.id.tv_noSppt);
        tvLokasiUsaha = findViewById(R.id.tv_lokasiUsaha);

        appSession = new AppSession(this);
        id = getIntent().getIntExtra(Intent.EXTRA_EMAIL,0);

        Log.d("id : ", ""+id);

        baseApiService.getDetailLetter(appSession.getData(AppSession.TOKEN), id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        ApiResponse status = new Gson().fromJson(result, ApiResponse.class);
                        if (status.getStatus().equalsIgnoreCase("success")){
                            JSONObject jsonObject = new JSONObject(result);
                            Log.d("detail: ", jsonObject.toString());
                            tvTitleSurat.setText(jsonObject.getString("application_letter_type"));
                            tvNomorSurat.setText("Nomor Surat : " + jsonObject.getString("id"));
                            tvName.setText("Nama : "+jsonObject.getString("name"));
                            tvTempat.setText("Tempat lahir: "+ jsonObject.getString("birth_district"));
                            tvTanggalLahir.setText("Tanggal lahir: "+ jsonObject.getString("birth_date"));

                            if (jsonObject.getString("gender").equalsIgnoreCase("L")){
                                tvJk.setText("Laki-laki");
                            }else if (jsonObject.getString("gender").equalsIgnoreCase("P")){
                                tvJk.setText("Perempuan");
                            }

                            tvStatusPerkawinan.setText("Status Perkawinan : "+jsonObject.getString("marriage_status"));
                            tvAlamatAsal.setText("Alamat Asal : " + jsonObject.getString("address"));
                            tvNamaPerusahaan.setText("Nama Perusahaan : " + jsonObject.getString("company_name"));
                            tvBidangUsaha.setText("Bidang Usaha : " + jsonObject.getString("business_type"));
                            tvStatusBangunan.setText("Status Bangunan : " + jsonObject.getString("building_status"));
                            tvTahunKegiatan.setText("Tahun Kegiatan : " + jsonObject.getString("starting_year"));
                            tvNoSppt.setText("No. SPPT : " + jsonObject.getString("sppt_number"));
                            tvLokasiUsaha.setText("Lokasi Usaha : " + jsonObject.getString("company_address"));
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
}