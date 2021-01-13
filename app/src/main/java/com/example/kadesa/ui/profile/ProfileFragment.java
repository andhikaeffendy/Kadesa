package com.example.kadesa.ui.profile;

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

import com.example.kadesa.LoginActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.AppSession;
import com.example.kadesa.model.User;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private AppSession appSession;
    private User user;
    private TextView tvName, tvEmail, tvProvinsi, tvKabupaten, tvKecamatan, tvPhone, tvPekerjaan, tvDesa, tvAddress, tvJenisKelamin, tvPassword;
    private Button btnGoToLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appSession = new AppSession(getActivity());
        ProfileViewModel profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = null;
        if (appSession.isLogin()) {
            root = inflater.inflate(R.layout.fragment_profile, container, false);
        } else {
            root = inflater.inflate(R.layout.activity_must_login, container, false);
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (appSession.isLogin()) {
            tvName = (TextView) view.findViewById(R.id.profileName);
            tvEmail = (TextView) view.findViewById(R.id.emailProfile);
            tvProvinsi = (TextView) view.findViewById(R.id.provinsiProfile);
            tvKabupaten = (TextView) view.findViewById(R.id.kabupatenProfile);
            tvKecamatan = (TextView) view.findViewById(R.id.kecamatanProfile);
            tvPhone = (TextView) view.findViewById(R.id.phoneProfile);
            tvPekerjaan = (TextView) view.findViewById(R.id.pekerjaanProfile);
            tvDesa = (TextView) view.findViewById(R.id.desaProfile);
            tvAddress = (TextView) view.findViewById(R.id.addressProfile);
            tvJenisKelamin = (TextView) view.findViewById(R.id.jenisKelaminProfile);
            tvPassword = (TextView) view.findViewById(R.id.passwordProfile);

            user = getUserSession();
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
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());
            tvPassword.setText("********");
            tvJenisKelamin.setText(user.getGender().equals("L") ? "Laki-laki" : "Perempuan");
            tvAddress.setText(user.getAddress());
            tvPhone.setText(user.getPhoneNumber());
        }
    }

    private User getUserSession(){
        return new Gson().fromJson(appSession.getData(AppSession.USER), User.class);
    }
}