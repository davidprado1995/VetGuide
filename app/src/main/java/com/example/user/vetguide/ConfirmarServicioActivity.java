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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConfirmarServicioActivity extends AppCompatActivity {
    Button butSiguiente, butCancelar;
    TextView tviServicioSeleccionado, tviNombreVetServicio;
    Spinner horasVet,diasVet;
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
        setContentView(R.layout.activity_confirmar_servicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i0=getIntent();
        tviServicioSeleccionado = (TextView)findViewById(R.id.tviServicioSeleccionado);
        tviServicioSeleccionado.setText(i0.getStringExtra("servicio"));
        tviNombreVetServicio = (TextView)findViewById(R.id.tviNombreVetServicio);
        tviNombreVetServicio.setText(i0.getStringExtra("nombreVetCita"));
        horasvet=i0.getStringExtra("horasVet").split("-");
        diasvet=i0.getStringExtra("diasVet").split("-");

        diasVet=(Spinner)findViewById(R.id.eteDiaServicio);

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
        diasVet.setAdapter(dataAdapter);

        contar=0;
        horasVet=(Spinner)findViewById(R.id.eteHoraServicio);

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
        horasVet.setAdapter(dataAdapter);

        butSiguiente = (Button)findViewById(R.id.buttonReservarServicio);
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(ConfirmarServicioActivity.this, "Reserva de servicio realizada.", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(ConfirmarServicioActivity.this, Inicio.class);
                startActivity(i);
            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar9);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(ConfirmarServicioActivity.this, "Reserva de servicio cancelada", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(ConfirmarServicioActivity.this, Inicio.class);
                startActivity(i);
            }
        });


    }

}
