package com.example.kadesa.ui.artikel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kadesa.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtikelAdapter extends ArrayAdapter<Artikel> {


    Picasso picasso;

    public ArtikelAdapter(@NonNull Context context, @NonNull List<Artikel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        picasso = Picasso.get();

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_artikel,parent, false);
        }

        Artikel current = getItem(position);
        ImageView imgArtikel = (ImageView) convertView.findViewById(R.id.et_imgArtikel);
        TextView nameArtikel = (TextView) convertView.findViewById(R.id.et_judulArtikel);

        picasso.load(current.getmImgArtikel()).into(imgArtikel);
        nameArtikel.setText(current.getmNamaArtikel());

        return convertView;
    }
}
