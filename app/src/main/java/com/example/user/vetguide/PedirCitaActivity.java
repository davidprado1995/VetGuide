package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class PedirCitaActivity extends AppCompatActivity {
    TextView tviNombreVet;
    Button butReservar, butCancelar;
    Spinner eteDiaCita,eteHoraCita;
    String idvet;
    String idUsuario;
    String[] horasvet;
    String[] diasvet;
    int contar=0;
    String[] dias={"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    List<String> diasausuar=new ArrayList<String>();
    String[] horas={"0:00","1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
    List<String> horasausuar=new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_cita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tviNombreVet=(TextView)findViewById(R.id.tviNombreVetCita);

        Intent i=getIntent();
        String nomb=i.getStringExtra("nombreVetCita");
        idUsuario = i.getStringExtra("idUsuario");
        idvet = i.getStringExtra("idVet");
        horasvet=i.getStringExtra("horasVet").split("-");
        diasvet=i.getStringExtra("diasVet").split("-");

        tviNombreVet.setText(nomb);

        butReservar=(Button)findViewById(R.id.buttonReservar);
        eteDiaCita=(Spinner)findViewById(R.id.eteDiaCita);

        for (String dia:dias){
            if(dia.equals(diasvet[0])) {
                break;
            }
            contar++;
        }

        for(int j=contar;j<dias.length; j++) {
            diasausuar.add(dias[j]);
            if(dias[j].equals(diasvet[1])){
                break;
            }
        }

        dataAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diasausuar);
        eteDiaCita.setAdapter(dataAdapter);

        contar=0;
        eteHoraCita=(Spinner)findViewById(R.id.eteHoraCita);

        for (String dia:horas){
            if(dia.equals(horasvet[0])) {
                break;
            }
            contar++;
        }

        for(int j=contar;j<horas.length; j++) {
            horasausuar.add(horas[j]);
            if(horas[j].equals(horasvet[1])){
                break;
            }
        }

        dataAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horasausuar);
        eteHoraCita.setAdapter(dataAdapter);

        butReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diaCita = eteDiaCita.getSelectedItem().toString();
                String horaCita = eteHoraCita.getSelectedItem().toString();
                ParseObject cita = new ParseObject("Cita");
                cita.put("horaCita", horaCita);
                cita.put("diaCita", diaCita);
                cita.put("idVeterinaria", idvet);
                cita.put("idUsuario", idUsuario);
                cita.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(PedirCitaActivity.this, "Reserva realizada", Toast.LENGTH_SHORT);
                            t.show();
                            Intent i2 = new Intent(PedirCitaActivity.this, Inicio.class);
                            startActivity(i2);
                        }
                    }
                });
            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar7);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(PedirCitaActivity.this, "Reserva cancelada", Toast.LENGTH_SHORT);
                t.show();
                Intent i3 = new Intent(PedirCitaActivity.this, Inicio.class);
                startActivity(i3);
            }
        });



    }

}
