package com.example.kadesa.helper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.example.kadesa.DetailArtikelActivity;
import com.example.kadesa.R;
import com.example.kadesa.helper.apihelper.UtilsApi;
import com.example.kadesa.model.ArtikelTerbaru;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtikelTerbaruAdapter extends RecyclerView.Adapter<ArtikelTerbaruAdapter.ViewHolder> {

    private Context context;
    private List<ArtikelTerbaru> artikelTerbaru;
    private Picasso picasso;

    public ArtikelTerbaruAdapter(Context context, List artikelTerbaru) {
        this.context = context;
        this.artikelTerbaru = artikelTerbaru;
        picasso = Picasso.get();
    }


    @NonNull
    @Override
    public ArtikelTerbaruAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artikel_terbaru, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(artikelTerbaru.get(position));
        ArtikelTerbaru artikelItem = artikelTerbaru.get(position);
        viewHolder.nameArtikel.setText(artikelItem.getmJudulArtikel());
        viewHolder.descArtikel.setText(artikelItem.getmDeskripsiArtikelTerbaru());
        picasso.load(artikelItem.getmImgArtikelTerbaru()).into(viewHolder.imgArtikel);

    }

    @Override
    public int getItemCount() {
        return artikelTerbaru.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgArtikel;
        TextView nameArtikel, descArtikel;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nameArtikel = (TextView) itemView.findViewById(R.id.et_namaArtikelTerbaru);
            descArtikel = (TextView) itemView.findViewById(R.id.et_deskripsiArtikelTerbaru);
            imgArtikel = (ImageView) itemView.findViewById(R.id.et_imgArtikelTerbaru);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ArtikelTerbaru mArtikel = (ArtikelTerbaru) v.getTag();
                            Intent intent = new Intent(context, DetailArtikelActivity.class);
                            intent.putExtra(Intent.EXTRA_EMAIL, mArtikel.getId());
                            context.startActivity(intent);
                            //Toast.makeText(v.getContext(), mArtikel.getmJudulArtikel() + " Is " + mArtikel.getmDeskripsiArtikelTerbaru(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }
}
