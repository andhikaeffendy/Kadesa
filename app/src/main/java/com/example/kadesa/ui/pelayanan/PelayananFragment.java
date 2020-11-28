package com.example.kadesa.ui.pelayanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kadesa.HistoryPermohonanKkActivity;
import com.example.kadesa.HistoryPermohonanSkuActivity;
import com.example.kadesa.LoginActivity;
import com.example.kadesa.R;

public class PelayananFragment extends Fragment {

    private PelayananViewModel pelayananViewModel;

    Button btnKtp;
    Button btnSKU;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        pelayananViewModel =
                ViewModelProviders.of(this).get(PelayananViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pelayanan, container, false);
        pelayananViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        btnKtp = (Button) root.findViewById(R.id.btn_ktp);
        btnSKU = (Button) root.findViewById(R.id.btn_sku);

        btnKtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryPermohonanSkuActivity.class);
                startActivity(intent);
            }
        });

        btnSKU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryPermohonanSkuActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}