package com.example.kadesa.helper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kadesa.R;
import com.example.kadesa.model.PermohonanSku;

import java.util.List;

public class PermohonanSkuAdapter extends ArrayAdapter<PermohonanSku> {
    public PermohonanSkuAdapter(@NonNull Context context,@NonNull List<PermohonanSku> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_permohonan_sku, parent,false);

        PermohonanSku current = getItem(position);
        TextView nomorSurat = (TextView) convertView.findViewById(R.id.tv_nomorSurat);
        TextView namaPerusahaan = (TextView) convertView.findViewById(R.id.tv_namaPerusahaan);
        TextView bidangUsaha = (TextView) convertView.findViewById(R.id.tv_bidangUsaha);

        nomorSurat.setText(current.getmNomorSurat());
        namaPerusahaan.setText(current.getmNamaPerusahaan());
        bidangUsaha.setText(current.getmBidangUsaha());

        return convertView;
    }
}
