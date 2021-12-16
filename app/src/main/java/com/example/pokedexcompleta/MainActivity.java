package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    //Jsoup --> JSON
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<String> urlsImg = new ArrayList<>();
    ListView listView;
    String urlPkm = "https://www.pokemon.com/es/pokedex/";
    String pathPkm = "[href^=/es/pokedex/]";//Patrón común

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listViewPkm);

        //----------PRE EJECUCIÓN------------//
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Document resCompleto = Jsoup.connect(urlPkm).get();
                    nombres = (ArrayList<String>) resCompleto.select(pathPkm).eachText();
                    nombres.remove(0); //En esta página el elemento 0 no nos vale

                    for (int i = 0; i < nombres.size(); i++)
                    {
                        String numPkm = String.format("%03d", i + 1);
                        //Conformar lista de urls
                        urlsImg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numPkm + ".png");
                        pokemons.add(new Pokemon(nombres.get(i))); //Conformar la lista de nombres
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                //------------POST EJECUCIÓN------------//
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        CustomAdapter adapter = new CustomAdapter(pokemons, MainActivity.this);
                        listView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}