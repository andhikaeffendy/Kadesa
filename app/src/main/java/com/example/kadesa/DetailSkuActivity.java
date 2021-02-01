package com.example.kadesa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
    private int idSurat, idBirth;
    private TextView tvName, tvTempat, tvTanggalLahir, tvJk, tvStatusPerkawinan, tvPekerjaan, tvAlamatAsal,
            tvNamaPerusahaan, tvBidangUsaha, tvStatusBangunan, tvTahunKegiatan, tvNoSppt, tvLokasiUsaha, tvTitleSurat, tvNomorSurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sku);

        appSession = new AppSession(getApplicationContext());
        idSurat = getIntent().getIntExtra("idSurat", 0);
        idBirth = getIntent().getIntExtra("idBirth", 0);
        Log.d("idSurat Detail" , ""+idSurat);
        Log.d("idBirth Detail" , ""+idBirth);

        getSupportActionBar().setTitle("Detail Permohonan");
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




        baseApiService.getDetailLetter(appSession.getData(AppSession.TOKEN), idSurat).enqueue(new Callback<ResponseBody>() {
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
                            tvName.setText("Nama : "+jsonObject.getString("name").toUpperCase());
                            tvTempat.setText("Tempat lahir: "+ jsonObject.getString("birth_district"));
                            //idBirth = Integer.parseInt(jsonObject.getString("birth_district_id"));
                            tvTanggalLahir.setText("Tanggal lahir: "+ jsonObject.getString("birth_date"));

                            if (jsonObject.getString("gender").equalsIgnoreCase("L")){
                                tvJk.setText("Jenis kelamin : Laki-laki");
                            }else if (jsonObject.getString("gender").equalsIgnoreCase("P")){
                                tvJk.setText("Jenis kelamin : Perempuan");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:
                showDialog();
                //Toast.makeText(getApplicationContext(), "Delete",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_edit:
                Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FormPermohonanSkuActivity.class);
                intent.putExtra("idSurat", idSurat);
                //idBirth = getIntent().getIntExtra("idBirth", 0);
                intent.putExtra("idBirth", idBirth);
                intent.putExtra("edit_mode", true);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                DetailSkuActivity.this);

        // set title dialog
        alertDialogBuilder.setTitle("Delete");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda yakin ingin Menghapus data ini?")
                .setIcon(R.drawable.ic_baseline_delete_24)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        baseApiService.deleteLetter(idSurat,appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    try {
                                        String result = response.body().string();
                                        ApiResponse status = new Gson().fromJson(result, ApiResponse.class);
                                        if (status.getStatus().equalsIgnoreCase("success")){
                                            Toast.makeText(getApplicationContext(), "Terhapus", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(DetailSkuActivity.this, PermohonanSkuActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(DetailSkuActivity.this,"Tidak terhapus", Toast.LENGTH_SHORT);
                                            return;
                                        }

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }else {
                                    Toast.makeText(DetailSkuActivity.this,"Tidak terhapus", Toast.LENGTH_SHORT);
                                    return;
                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(DetailSkuActivity.this,"Tidak terhapus", Toast.LENGTH_SHORT);
                                return;
                            }
                        });
                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // jika tombol ini diklik, akan menutup dialog
                        // dan tidak terjadi apa2
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();

        // menampilkan alert dialog
        alertDialog.show();
    }
}