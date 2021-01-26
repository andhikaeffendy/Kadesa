package com.example.kadesa.helper.adapter;

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
import com.example.kadesa.model.IndahDesaku;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IndahDesakuAdapter extends ArrayAdapter<IndahDesaku> {

    public IndahDesakuAdapter(Context context, List objects) {
        super(context, 0, objects);
    }

    Picasso picasso;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        picasso = Picasso.get();
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_indah_desaku, parent, false);
        }

        IndahDesaku current = getItem(position);
        ImageView img = (ImageView) convertView.findViewById(R.id.et_imgDesa);
        TextView judul = (TextView) convertView.findViewById(R.id.et_namaDesa);
        picasso.load(current.getmImg()).into(img);
        judul.setText(current.getmJudul());

        return convertView;
    }
}
