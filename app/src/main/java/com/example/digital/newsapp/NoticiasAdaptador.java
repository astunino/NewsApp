package com.example.digital.newsapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NoticiasAdaptador extends RecyclerView.Adapter {

    private ArrayList<Noticia> noticias;

    public NoticiasAdaptador(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.item_layout,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Noticia noticia = noticias.get(position);

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.bind(noticia);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewFoto;
        private TextView textViewTitulo;
        private TextView textViewDescripcion;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewFoto = itemView.findViewById(R.id.imageViewFoto);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
        }

        public void bind(Noticia noticia){
            textViewTitulo.setText(noticia.getTitulo());
            textViewDescripcion.setText(noticia.getContenido());

        }

    }
}
