package com.example.kadesa.helper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kadesa.R;
import com.example.kadesa.model.LembagaDesa;

import java.util.List;

public class LembagaDesaAdapter extends ArrayAdapter<LembagaDesa> {

    public LembagaDesaAdapter(@NonNull Context context, @NonNull List<LembagaDesa> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_lembaga_desa, parent, false);
        }



        return convertView;
    }
}
