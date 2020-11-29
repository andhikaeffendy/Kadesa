package com.example.kadesa.ui.pemerintah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kadesa.R;

import java.util.ArrayList;
import java.util.List;

public class PemerintahAdapter extends ArrayAdapter<Pemerintah> {


    public PemerintahAdapter(@NonNull Context context, @NonNull List<Pemerintah> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_layout_pemerintah, parent, false);
        }

        Pemerintah current = getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.icon_listPemerintah);
        name.setText(current.getName());

        return convertView;
    }
}
