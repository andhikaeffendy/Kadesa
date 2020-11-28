package com.example.kadesa.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kadesa.LoginActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.AppSession;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    AppSession appSession;
    private ProfileViewModel profileViewModel;

    Button btnLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        appSession = new AppSession(getActivity());

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = null;

        if (appSession.isLogin()){
            root = inflater.inflate(R.layout.fragment_profile, container, false);
        }else {
            root = inflater.inflate(R.layout.activity_must_login, container, false);
            btnLogin = (Button) root.findViewById(R.id.btn_goToLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        return root;
    }
}