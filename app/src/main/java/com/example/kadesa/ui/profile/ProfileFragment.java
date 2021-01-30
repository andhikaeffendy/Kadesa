package com.example.kadesa.ui.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kadesa.LoginActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.AppSession;
import com.example.kadesa.helper.apihelper.BaseApiService;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ApiResponse;
import com.example.kadesa.model.User;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private AppSession appSession;
    private User user;
    private TextView tvName, tvEmail, tvPhone, tvPekerjaan, tvDesa, tvAddress, tvJenisKelamin;
    private Button btnGoToLogin, btnLogout;
    private CircleImageView imvProfilePict;
    Picasso picasso;
    private final BaseApiService baseApiService = UtilsApi.getApiService();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appSession = new AppSession(getActivity());
        ProfileViewModel profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = null;

        if (appSession.isLogin() && appSession.getData(AppSession.TOKEN) != null ) {
            root = inflater.inflate(R.layout.fragment_profile, container, false);
        } else {
            appSession.logout();
            root = inflater.inflate(R.layout.activity_must_login, container, false);
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = (TextView) view.findViewById(R.id.profileName);
        tvEmail = (TextView) view.findViewById(R.id.emailProfile);
        tvPhone = (TextView) view.findViewById(R.id.phoneProfile);
        tvPekerjaan = (TextView) view.findViewById(R.id.pekerjaanProfile);
        tvDesa = (TextView) view.findViewById(R.id.desaProfile);
        tvAddress = (TextView) view.findViewById(R.id.addressProfile);
        tvJenisKelamin = (TextView) view.findViewById(R.id.jenisKelaminProfile);
        btnLogout = (Button) view.findViewById(R.id.btnLogout);
        imvProfilePict = (CircleImageView) view.findViewById(R.id.imvProfilePict);
        picasso = Picasso.get();

        if (appSession.isLogin()) {
            user = getUserSession();
            baseApiService.getProfileUser(appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            String result = response.body().string();
                            ApiResponse status = new Gson().fromJson(result, ApiResponse.class);
                            Log.d("data " , result);
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getString("status").equalsIgnoreCase("success")){
                                tvName.setText(jsonObject.getString("name"));
                                tvEmail.setText(jsonObject.getString("email"));
                                tvAddress.setText(jsonObject.getString("address"));
                                tvDesa.setText(jsonObject.getString("village_name"));
                                tvPhone.setText(jsonObject.getString("phone_number"));
                                tvPekerjaan.setText(jsonObject.getString("occupation_name"));
                                if (jsonObject.getString("gender").equalsIgnoreCase("L")){
                                    tvJenisKelamin.setText("Laki-laki");
                                }else if (jsonObject.getString("gender").equalsIgnoreCase("P")){
                                    tvJenisKelamin.setText("Perempuan");
                                }else {
                                    tvJenisKelamin.setText(jsonObject.getString("gender"));
                                }
                                //picasso.load(jsonObject.getString("image")).into(imvProfilePict);

                            }else {
                                //appSession.logout();
                                Toast.makeText(getActivity(), "salah", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "salah", Toast.LENGTH_SHORT).show();
                    return;
                }
            });


            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });

            //Log.d("profile", user.getName());

//            tvName.setText(user.getName());
//            tvEmail.setText(user.getEmail());
////            tvProvinsi.setText(user.getVillageId());
//            tvPhone.setText(user.getPhoneNumber());
//            tvAddress.setText(user.getAddress());
//            tvJenisKelamin.setText(user.getGender());
//            tvKabupaten.setText(user.getOccupationId());

            initData();
        }else{
            btnGoToLogin = (Button) view.findViewById(R.id.btn_goToLogin);
            btnGoToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

    private void initData() {
        if (appSession.isLogin()) {

        }
    }

    private User getUserSession(){
        return new Gson().fromJson(appSession.getData(AppSession.USER), User.class);
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title dialog
        alertDialogBuilder.setTitle("Logout");

        // set pesan dari dialog
        alertDialogBuilder
                .setMessage("Anda yakin ingin logout?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        baseApiService.logoutRequest(appSession.getData(AppSession.TOKEN)).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                appSession.logout();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getActivity(),"Masih belum logout", Toast.LENGTH_SHORT);
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