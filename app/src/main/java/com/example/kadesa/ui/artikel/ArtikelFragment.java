package com.example.kadesa.ui.artikel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kadesa.DetailArtikelActivity;
import com.example.kadesa.R;
import com.example.kadesa.ui.artikel.ArtikelViewModel;

import java.util.ArrayList;

public class ArtikelFragment extends Fragment {

    private ArtikelViewModel artikelViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.list_artikel, container,false);

        ArrayList<Artikel> artikels = new ArrayList<>();
        artikels.add(new Artikel(R.drawable.ic_baseline_image_24, "Desa Kamuflase di bojongsoang"));
        artikels.add(new Artikel(R.drawable.ic_baseline_image_24, "Desa Kamuflase di bojongsoang"));
        artikels.add(new Artikel(R.drawable.ic_baseline_image_24, "Desa Kamuflase di bojongsoang"));
        artikels.add(new Artikel(R.drawable.ic_baseline_image_24, "Desa Kamuflase di bojongsoang"));
        artikels.add(new Artikel(R.drawable.ic_baseline_image_24, "Desa Kamuflase di bojongsoang"));

        ListView list = view.findViewById(R.id.list_artikel);

        final ArtikelAdapter adapter = new ArtikelAdapter(getActivity(), artikels);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                adapter.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
