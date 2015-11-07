package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class DetellaVeterinaria extends AppCompatActivity {

    TextView nombrevetdetalle,direccionvetdetalle,telefonovetdetalle,horariovetdetalle, nombredocdetalle,especialidaddocdetalle,
            diasatenciondocdetalle,horasatenciondocdetalle;
    String codigovetdetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detella_veterinaria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nombrevetdetalle = (TextView)findViewById(R.id.tviNombreVet);
        direccionvetdetalle = (TextView)findViewById(R.id.tviDireccionVet);
        telefonovetdetalle = (TextView)findViewById(R.id.tviTelefonoVet);
        horariovetdetalle = (TextView)findViewById(R.id.tviHorarioAttVet);

        nombredocdetalle = (TextView)findViewById(R.id.tviNombreDoc);
        especialidaddocdetalle =(TextView)findViewById(R.id.tviEspecialidadDoc);
        horasatenciondocdetalle = (TextView)findViewById(R.id.tviHorasAtencionDoc);
        diasatenciondocdetalle =(TextView)findViewById(R.id.tviDiasAtencionDoc);

        Intent i0 = getIntent();
        codigovetdetalle = i0.getStringExtra("idvet");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");
        query.whereEqualTo("objectId", codigovetdetalle);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {
                        String nombrevet = o.getString("nombre");
                        String direccionvet = o.getString("direccion");

                        String distritovet = o.getString("distrito");

                        Integer telefonovet = o.getInt("telefono");
                        String horasvet = o.getString("horas_atencion");
                        nombrevetdetalle.setText(nombrevet);
                        direccionvetdetalle.setText(direccionvet);
                        telefonovetdetalle.setText(telefonovet);
                        horariovetdetalle.setText(horasvet);
                    }
                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Doctor");
        query2.whereEqualTo("objectId",codigovetdetalle);
        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    for(ParseObject o2:objects){
                        String nombredoc = o2.getString("nombre");
                        String especialidaddoc = o2.getString("especialidad");
                        String horariodoc = o2.getString("horario_atencion");
                        String tiempodoc = o2.getString("tiempo_atencion");

                        nombredocdetalle.setText(nombredoc);
                        especialidaddocdetalle.setText(especialidaddoc);
                        horasatenciondocdetalle.setText(horariodoc);
                        diasatenciondocdetalle.setText(tiempodoc);
                    }
                }
            }
        });


    }

}
