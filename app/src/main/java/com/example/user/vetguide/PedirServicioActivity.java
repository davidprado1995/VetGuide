package com.example.user.vetguide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PedirServicioActivity extends AppCompatActivity {
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_servicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ll = (LinearLayout)findViewById(R.id.linearLayoutServ);
        createRadioButton();

    }

    private void createRadioButton() {
        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<5; i++){
            rb[i]  = new RadioButton(this);

            rb[i].setText("Servicio" + i);
            rb[i].setId(i + 100);
            rg.addView(rb[i]);
        }
        ll.addView(rg);//you add the whole RadioGroup to the layout

    }


}
