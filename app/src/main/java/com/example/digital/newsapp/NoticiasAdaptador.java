package com.example.digital.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
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

        for(int i=0; i<noticias.size();i++){
            new GetBitmap(i).execute();
        }

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

            //new DownloadImageTask(imageViewFoto).execute(noticia.getUrlImagen());

            imageViewFoto.setImageBitmap(noticia.getBitmapImagen());
            textViewTitulo.setText(noticia.getTitulo());
            textViewDescripcion.setText(noticia.getContenido());

        }

    }

    @SuppressLint("StaticFieldLeak")
    private class GetBitmap extends AsyncTask<Integer, Void, Bitmap> {

        private int posicion;

        public GetBitmap(int posicion) {
            this.posicion = posicion;
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {

            Bitmap bitImagen = CargarImagenes(noticias.get(posicion).getUrlImagen());

            return bitImagen;
        }

        protected void onPostExecute(Bitmap bitImagen) {
            noticias.get(posicion).setBitmapImagen(bitImagen);
            notifyDataSetChanged();
        }
    }

    public Bitmap CargarImagenes(String urlImagen){

        Bitmap bitImagen = null;

        if(!urlImagen.equals("null")) {

            InputStream in = null;
            try {
                in = new java.net.URL(urlImagen).openStream();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bitImagen = BitmapFactory.decodeStream(in);
        }

        return bitImagen;
    }
}
