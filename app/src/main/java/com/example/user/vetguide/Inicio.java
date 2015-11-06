package com.example.user.vetguide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import clases.Veterinaria;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView rvi=(RecyclerView)findViewById(R.id.my_recycler_view);
        rvi.setLayoutManager(new LinearLayoutManager(this));
        final List<Veterinaria> lista = new ArrayList<>();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");
        /*query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    Toast t=Toast.makeText(getBaseContext(), objects.size() + "" , Toast.LENGTH_SHORT);
                    t.show();
                    for(int i=0;i<objects.size();i++){
                        ParseObject po = objects.get(i);
                        String nombrevet = po.getString("nombre");
                        String direccionvet = po.getString("direccion");
                        long numero = po.getLong("telefono");
                        String diasatencion=po.getString("dias_atencion");
                        String horasatencion=po.getString("horas_atencion");
                        List<String> mascotas=po.getList("TipoMascota");
                        List<String> servicios=po.getList("ServiciosBrindados");
                        double lon=0.0;
                        double lat=0.0;
                        Veterinaria vet=new Veterinaria(nombrevet,direccionvet,numero,horasatencion,diasatencion,mascotas,servicios,lon,lat);
                        lista.add(vet);

                    }
                }
            }
        });*/
        List<ParseObject> results = new ArrayList<>();
        try {
            results = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast t=Toast.makeText(getBaseContext(), results.size() + "" , Toast.LENGTH_SHORT);
        t.show();
        for(int i=0;i<results.size();i++){
            ParseObject po = results.get(i);
            String nombrevet = po.getString("nombre");
            String direccionvet = po.getString("direccion");
            long numero = po.getLong("telefono");
            String diasatencion=po.getString("dias_atencion");
            String horasatencion=po.getString("horas_atencion");
            List<String> mascotas=po.getList("TipoMascota");
            List<String> servicios=po.getList("ServiciosBrindados");
            double lon=0.0;
            double lat=0.0;
            Veterinaria vet=new Veterinaria(nombrevet,direccionvet,numero,horasatencion,diasatencion,mascotas,servicios,lon,lat);
            lista.add(vet);

        }

        VeterinariaAdapter vad=new VeterinariaAdapter(lista);
        vad.SetOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veterinaria ve= (Veterinaria)v.getTag();


            }
        });
        rvi.setAdapter(vad);



    }



}
