package com.example.kadesa.helper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kadesa.R;
import com.example.kadesa.model.IndahDesaku;
import com.example.kadesa.model.MemberPemerintah;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LembagaAdapter extends ArrayAdapter<MemberPemerintah> {

    public LembagaAdapter(@NonNull Context context, @NonNull List<MemberPemerintah> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Picasso picasso;
        picasso = Picasso.get();
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_lembaga_desa, parent, false);
        }

        MemberPemerintah current = getItem(position);
        ImageView img = (ImageView) convertView.findViewById(R.id.et_imgLembaga);
        TextView nameLembaga = convertView.findViewById(R.id.et_namaLembaga);
        TextView jabatanLembaga = convertView.findViewById(R.id.et_jabatanLembaga);

        if (!current.getPhoto().equals("")) {
            picasso.load(current.getPhoto()).into(img);
        }
        nameLembaga.setText(current.getName());
        jabatanLembaga.setText(current.getDescription());

        return convertView;
    }
}
