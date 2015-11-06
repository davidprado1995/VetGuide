package com.example.user.vetguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.parse.FindCallback;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvi=(RecyclerView)findViewById(R.id.my_recycler_view);
        rvi.setLayoutManager(new LinearLayoutManager(this));



        //Toast.makeText(this,"Estamos aqui",Toast.LENGTH_LONG).show();
        adapter=new VeterinariaAdapter(lista);
        rvi.setAdapter(adapter);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                for(ParseObject object:objects){
                    Toast.makeText(Inicio.this, object.get("nombre").toString(), Toast.LENGTH_SHORT).show();
                    //Mostrar la lista de veterinarias

//                        ParseObject po = objects.get(i);
//                      System.out.println(po);
//                        String nombrevet = po.getString("nombre");
//                        String direccionvet = po.getString("direccion");
//                        long numero = po.getLong("telefono");
//                       String diasatencion=po.getString("dias_atencion");
//                       String horasatencion=po.getString("horas_atencion");
//                       List<String> mascotas=po.getList("TipoMascota");
//                    List<String> servicios=po.getList("ServiciosBrindados");
//                       double lon=0.0;
//                        double lat=0.0;
//                    Veterinaria vet=new Veterinaria(nombrevet,direccionvet,numero,horasatencion,diasatencion,mascotas,servicios,lon,lat);
//                    lista.add(vet);
                    lista.add(object);
                }
                adapter.notifyDataSetChanged();

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
