package com.example.digital.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticiaDetalleActivity extends AppCompatActivity {

    public static final String KEY_TITULO = "titulo";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_IMAGEN = "imagen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_detalle);

        ImageView imageViewNoticia = findViewById(R.id.imageViewFoto);
        TextView textViewTitulo = findViewById(R.id.textViewTitulo);
        TextView textViewDescripcion = findViewById(R.id.textViewDescripcion);

        byte[] byteArray = getIntent().getExtras().getByteArray(KEY_IMAGEN);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageViewNoticia.setImageBitmap(bmp);
        textViewTitulo.setText(getIntent().getExtras().getInt(KEY_TITULO));
        textViewDescripcion.setText(getIntent().getExtras().getInt(KEY_DESCRIPCION));

    }
}
