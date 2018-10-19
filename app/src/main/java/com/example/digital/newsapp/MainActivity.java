package com.example.digital.newsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoticiasAdaptador.NoticiaReceptor{

    private String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private RecyclerView recyclerViewNoticias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewNoticias = findViewById(R.id.recyclerViewNoticias);

        new GetNoticias().execute();


    }

    @Override
    public void recibir(Noticia noticia) {
        Bundle bundle = new Bundle();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        noticia.getBitmapImagen().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Bundle b = new Bundle();

        bundle.putString(NoticiaDetalleActivity.KEY_TITULO,noticia.getTitulo());
        bundle.putString(NoticiaDetalleActivity.KEY_DESCRIPCION,noticia.getDescripcion());
        bundle.putByteArray(NoticiaDetalleActivity.KEY_IMAGEN,byteArray);

        Intent intent = new Intent(this,NoticiaDetalleActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @SuppressLint("StaticFieldLeak")
    private class GetNoticias extends AsyncTask<Void, Void, ArrayList<Noticia>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Noticia> doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting responseç
            String url = "https://newsapi.org/v2/top-headlines?country=ar&category=science&apiKey=8b141017cf6848908829489044ed6f71";
            //String url = "https://newsapi.org/v2/everything?q=cyber&apiKey=8b141017cf6848908829489044ed6f71";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray noticiasJSON = jsonObj.getJSONArray("articles");

                    // looping through All Entidades
                    for (Integer i = 0; i < noticiasJSON.length(); i++) {
                        JSONObject e = noticiasJSON.getJSONObject(i);
                        String autor = e.getString("author");
                        String titulo = e.getString("title");
                        String descripcion = e.getString("description");
                        String urlNoticia = e.getString("url");
                        String urlImage = e.getString("urlToImage");
                        if(urlImage.startsWith("//")){
                            urlImage = "http:"+urlImage;
                        }
                        String publicado = e.getString("publishedAt");
                        String contenido = e.getString("content");

                        Noticia noticia = new Noticia(autor,titulo,descripcion,urlNoticia,urlImage,publicado,contenido);

                        noticias.add(noticia);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Sin conexión",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return noticias;
        }

        @Override
        protected void onPostExecute(ArrayList<Noticia> result) {
            super.onPostExecute(result);

            traerDatos();
        }
    }

    public void traerDatos(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerViewNoticias.setHasFixedSize(true);

        NoticiasAdaptador adaptador = new NoticiasAdaptador(noticias);
        recyclerViewNoticias.setAdapter(adaptador);
        recyclerViewNoticias.setLayoutManager(layoutManager);
    }

}
