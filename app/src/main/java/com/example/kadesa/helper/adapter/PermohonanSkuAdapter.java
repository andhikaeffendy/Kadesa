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
        TextView formulirKK = (TextView) convertView.findViewById(R.id.tv_formulirKartuKeluarga);
        TextView tanggal = (TextView) convertView.findViewById(R.id.tv_tanggal);
        TextView status = (TextView) convertView.findViewById(R.id.tv_statusSurat);
        TextView idSurat = (TextView) convertView.findViewById(R.id.tv_idSurat);

        formulirKK.setText(current.getmFormulirKK());
        tanggal.setText(current.getmTanggal());
        status.setText(current.getmStatus());
        idSurat.setText(""+current.getmIdSurat());

        return convertView;
    }
}
