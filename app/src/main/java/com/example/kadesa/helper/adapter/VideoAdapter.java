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
import com.example.kadesa.model.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends ArrayAdapter<Video> {

    Picasso picasso;

    public VideoAdapter(@NonNull Context context, @NonNull List<Video> objects) {

        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        picasso = Picasso.get();
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_video, parent, false);
        }

        Video current = getItem(position);
        ImageView img = (ImageView) convertView.findViewById(R.id.et_tempVideo);
        TextView judul = (TextView) convertView.findViewById(R.id.et_judulVideo);

        picasso.load(current.getmImgVideo()).into(img);
        judul.setText(current.getmJudulVideo());

        return convertView;
    }
}
