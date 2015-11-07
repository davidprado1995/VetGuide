package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import clases.Veterinaria;

public class Inicio extends AppCompatActivity {
    ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");
    VeterinariaAdapter adapter;
    List<ParseObject> lista = new ArrayList<>();//lo cambie por un ParseObject
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvi=(RecyclerView)findViewById(R.id.my_recycler_view);
        rvi.setLayoutManager(new LinearLayoutManager(this));







        adapter=new VeterinariaAdapter(lista);
        rvi.setAdapter(adapter);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                for(ParseObject object:objects){
                    Toast.makeText(Inicio.this, object.get("nombre").toString(), Toast.LENGTH_SHORT).show();
                    lista.add(object);
                }
                adapter.notifyDataSetChanged();

            }

        });




        VeterinariaAdapter vad=new VeterinariaAdapter(lista);
        vad.SetOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject ve = (ParseObject)v.getTag();
                Intent i = new Intent(Inicio.this, DetellaVeterinaria.class);
                String nombre=ve.getString("nombre");
                i.putExtra("nombre",nombre);
                startActivity(i);
            }
        });

//        VeterinariaAdapter vad=new VeterinariaAdapter(lista);
//        vad.SetOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        rvi.setAdapter(vad);



    }



}
