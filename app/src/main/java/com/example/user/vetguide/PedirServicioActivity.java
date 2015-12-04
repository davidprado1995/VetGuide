package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PedirServicioActivity extends AppCompatActivity {
    LinearLayout ll;
    String codigovetdetalle,nombrevet;
    JSONArray servicios = new JSONArray();
    Button butSiguiente, butCancelar;
    List<RadioButton> rb=new ArrayList<>();
    String hora,dia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_servicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i0 = getIntent();
        codigovetdetalle = i0.getStringExtra("idVet");
        nombrevet = i0.getStringExtra("nombreVetCita");
        hora=i0.getStringExtra("horasVet");
        dia=i0.getStringExtra("diasVet");

        ll = (LinearLayout)findViewById(R.id.linearLayoutServ);
        final RadioGroup rg = createRadioButton();

        butSiguiente = (Button)findViewById(R.id.buttonSiguiente8);
        butCancelar = (Button)findViewById(R.id.buttonCancelar8);

        butSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PedirServicioActivity.this, ConfirmarServicioActivity.class);
                i.putExtra("servicio",rb.get(rg.getCheckedRadioButtonId()).getText());
                i.putExtra("nombreVetCita",nombrevet);
                i.putExtra("horasVet",hora);
                i.putExtra("diasVet",dia);
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

    public RadioGroup createRadioButton() {
        final RadioGroup rg = new RadioGroup(PedirServicioActivity.this);
         //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        rg.setGravity(Gravity.CENTER_HORIZONTAL);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Veterinaria");
        query.whereEqualTo("objectId", codigovetdetalle);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject o : objects) {
                        servicios = o.getJSONArray("ServiciosBrindados");
                        for (int i = 0; i < servicios.length(); i++) {
                            RadioButton rad = new RadioButton(PedirServicioActivity.this);
                            try {

                                rad.setText(servicios.getString(i));

                            } catch (JSONException je) {
                                System.out.println(je);
                            }

                            rad.setId(i);
                            rb.add(rad);
                            rg.addView(rad);
                        }
                    }
                }
            }
        });

        ll.addView(rg);//you add the whole RadioGroup to the layout
        return rg;
    }


}
