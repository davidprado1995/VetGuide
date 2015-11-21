package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmarServicioActivity extends AppCompatActivity {
    Button butSiguiente, butCancelar;
    TextView tviServicioSeleccionado, tviNombreVetServicio;

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
        butSiguiente = (Button)findViewById(R.id.buttonReservarServicio);
        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(ConfirmarServicioActivity.this, "Reserva de servicio realizada.", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(ConfirmarServicioActivity.this, DetellaVeterinaria.class);
                startActivity(i);
            }
        });

        butCancelar=(Button)findViewById(R.id.buttonCancelar9);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(ConfirmarServicioActivity.this, "Reserva de servicio cancelada", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(ConfirmarServicioActivity.this, PedirServicioActivity.class);
                startActivity(i);
            }
        });


    }

}
