package com.example.user.vetguide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class RegistrarVeterinariaFragment extends Fragment {

    Button butSiguiente, butCancelar, butMapa;
    EditText nombrevete, direccionvete, distritovete, telefonovete;
    Spinner diasinicio, diasfin, horasinicio, horasfin;
    ParseObject veterinaria;
    double latitud, longitud;

    static int UBICACION = 1000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registrar_veterinaria, container, false);

        butSiguiente = (Button) view.findViewById(R.id.buttonSiguiente3);
        butMapa = (Button) view.findViewById(R.id.buttonMapear);

        nombrevete = (EditText) view.findViewById(R.id.eteNombreVet);
        direccionvete = (EditText) view.findViewById(R.id.eteDireccionVet);
        distritovete = (EditText) view.findViewById(R.id.eteDistritoVet);
        telefonovete = (EditText) view.findViewById(R.id.eteTelefonoVet);
        diasinicio = (Spinner) view.findViewById(R.id.spDiaInicio);
        diasfin = (Spinner) view.findViewById(R.id.eteDiaFinVet);
        horasinicio = (Spinner) view.findViewById(R.id.eteHoraInicioVet);
        horasfin = (Spinner) view.findViewById(R.id.eteHoraFinVet);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veterinaria = new ParseObject("Veterinaria");


                veterinaria.put("nombre", nombrevete.getText().toString());
                veterinaria.put("direccion", direccionvete.getText().toString());
                veterinaria.put("distrito", distritovete.getText().toString());
                veterinaria.put("telefono", Integer.parseInt(telefonovete.getText().toString()));
                veterinaria.put("dias_atencion", diasinicio.getItemAtPosition(diasinicio.getSelectedItemPosition()).toString() + "-" + diasfin.getItemAtPosition(diasfin.getSelectedItemPosition()).toString());
                veterinaria.put("horas_atencion", horasinicio.getItemAtPosition(horasinicio.getSelectedItemPosition()).toString() + "-" + horasfin.getItemAtPosition(horasfin.getSelectedItemPosition()).toString());
                veterinaria.put("latitud",latitud);
                veterinaria.put("longitud",longitud);
                veterinaria.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(getContext(), "Inscripción de Veterinaria Correcta", Toast.LENGTH_SHORT);
                            t.show();
                            Intent i = new Intent(getActivity(), MascotasVetActivity.class);
                            i.putExtra("codigovet", veterinaria.getObjectId());
                            startActivity(i);
                        } else {
                            Toast t = Toast.makeText(getContext(), "Inscripción de Veterinaria Incorrecta", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                });


            }
        });


        butCancelar = (Button) view.findViewById(R.id.buttonCancelar3);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        butMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i0 = new Intent(getActivity(), MapsActivity.class);
                i0.putExtra("dir",direccionvete.getText().toString());
                startActivityForResult(i0,UBICACION);


            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == UBICACION){
            if(resultCode == Activity.RESULT_OK){
                latitud = data.getDoubleExtra("latitud",0);
                longitud =data.getDoubleExtra("longitud",0);

            }
        }

    }
}
