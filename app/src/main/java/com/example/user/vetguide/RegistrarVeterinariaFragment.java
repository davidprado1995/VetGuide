package com.example.user.vetguide;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class RegistrarVeterinariaFragment extends Fragment {

    Button butSiguiente,butCancelar;
    EditText nombrevete,direccionvete,distritovete,telefonovete,diasinicio,diasfin,horasinicio,horasfin;
    ParseObject veterinaria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registrar_veterinaria, container, false);

        butSiguiente=(Button)view.findViewById(R.id.buttonSiguiente3);

        nombrevete = (EditText)view.findViewById(R.id.eteNombreVet);
        direccionvete = (EditText)view.findViewById(R.id.eteDireccionVet);
        distritovete = (EditText)view.findViewById(R.id.eteDistritoVet);
        telefonovete = (EditText)view.findViewById(R.id.eteTelefonoVet);
        diasinicio = (EditText)view.findViewById(R.id.eteDiaInicioVet);
        diasfin = (EditText)view.findViewById(R.id.eteDiaFinVet);
        horasinicio = (EditText)view.findViewById(R.id.eteHoraInicioVet);
        horasfin = (EditText)view.findViewById(R.id.eteHoraFinVet);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               veterinaria= new ParseObject("Veterinaria");


                veterinaria.put("nombre",nombrevete.getText().toString());
                veterinaria.put("direccion",direccionvete.getText().toString());
                veterinaria.put("distrito",distritovete.getText().toString());
                veterinaria.put("telefono",Integer.parseInt(telefonovete.getText().toString()));
                veterinaria.put("dias_atencion",diasinicio.getText().toString() + "-" + diasfin.getText().toString());
                veterinaria.put("horas_atencion", horasinicio.getText().toString() + "-" + horasfin.getText().toString());
                veterinaria.saveInBackground(new SaveCallback() {

                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast t = Toast.makeText(getContext(), "Inscripción de Veterinaria Correcta", Toast.LENGTH_SHORT);
                            t.show();
                            Intent i = new Intent(getActivity(), MascotasVetActivity.class);
                            i.putExtra("codigovet",veterinaria.getObjectId());
                            startActivity(i);
                        } else {
                            Toast t = Toast.makeText(getContext(), "Inscripción de Veterinaria Incorrecta", Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                });



            }
        });


        butCancelar=(Button)view.findViewById(R.id.buttonCancelar3);
        butCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });

        return view;
    }



}
