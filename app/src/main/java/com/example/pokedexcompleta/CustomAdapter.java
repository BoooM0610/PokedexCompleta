package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{
    ArrayList<Pokemon> pokemons;
    Context ctx;

    public CustomAdapter(ArrayList<Pokemon> pokemons, Context ctx)
    {
        this.pokemons = pokemons;
        this.ctx = ctx;
    }

    @Override
    public int getCount()
    {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int position)
    {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View viewInflado = LayoutInflater.from(ctx).inflate(R.layout.item_pkm, null);
        TextView nombre = viewInflado.findViewById(R.id.textViewNombre);
        ImageView imagePkm = viewInflado.findViewById(R.id.imageViewPkm);
        nombre.setText(pokemons.get(position).getNombre());
        Picasso.get().load(MainActivity.urlsImg.get(position)).into(imagePkm);
        return viewInflado;
    }
}
