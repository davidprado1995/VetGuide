package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PedirServicioActivity extends AppCompatActivity {
    LinearLayout ll;
    String codigovetdetalle;
    List<String> servicios = new ArrayList<>();
    Button butSiguiente, butCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_servicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i0 = getIntent();
        codigovetdetalle = i0.getStringExtra("idVet");

        ll = (LinearLayout)findViewById(R.id.linearLayoutServ);
        createRadioButton(codigovetdetalle);

        butSiguiente = (Button)findViewById(R.id.buttonSiguiente8);
        butCancelar = (Button)findViewById(R.id.buttonCancelar8);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PedirServicioActivity.this, ConfirmarServicioActivity.class);
                startActivity(i);
            }
        });

        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PedirServicioActivity.this, DetellaVeterinaria.class);
                startActivity(i);
            }
        });

    }

    public void createRadioButton(String codigovet) {
        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");
        query.whereEqualTo("objectId", codigovetdetalle);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {
                        servicios = o.getList("servicios");
                    }
                }
            }
        });

        for(int i=0; i<servicios.size(); i++){
            rb[i]  = new RadioButton(this);

            rb[i].setText(servicios.get(i));
            rb[i].setId(i + 100);
            rg.addView(rb[i]);
        }
        ll.addView(rg);//you add the whole RadioGroup to the layout

    }


}
