package com.example.kadesa.ui.pemerintah;

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

import com.example.kadesa.ProfileLembagaActivity;
import com.example.kadesa.R;

public class PemerintahFragment extends Fragment {

    private PemerintahViewModel pemerintahViewModel;

    Button btnLembaga;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pemerintahViewModel =
                ViewModelProviders.of(this).get(PemerintahViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pemerintah, container, false);
        pemerintahViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        btnLembaga = (Button) root.findViewById(R.id.btn_lembaga1);
        btnLembaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileLembagaActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}