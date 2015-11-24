package com.example.user.vetguide;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnBuscar;
    EditText eteDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent i = getIntent();
        i.getExtras();
        String dir = i.getStringExtra("dir");
        Double latitud = i.getDoubleExtra("latitud", 0);
        Double longitud = i.getDoubleExtra("longitud", 0);
        LatLng a = getLocationFromAddress(getBaseContext(), dir);

        btnBuscar = (Button) findViewById(R.id.butBuscar);
        eteDireccion = (EditText) findViewById(R.id.eteDir);


        if (latitud == 0 && longitud == 0) {
            //Signifa que vino de registro
            if (a == null) {
                Toast.makeText(this, "No se encontro la direccion", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, a.latitude + "," + a.longitude, Toast.LENGTH_LONG).show();
            }

        } else {
            //Significa que vino de mostrar
            btnBuscar.setVisibility(View.GONE);
            eteDireccion.setVisibility(View.GONE);
        }


//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a, 18));//te mueve a la poscion encontrada
//        mMap.addMarker(new MarkerOptions().position(a));


        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng c = getLocationFromAddress(getBaseContext(), eteDireccion.getText().toString());
                Toast.makeText(MapsActivity.this, "Latitud: " + c.latitude + " ,Longitud: " + c.longitude, Toast.LENGTH_LONG).show();
                //c=geoPoint----------18=zoom de la vista del mapa
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(c, 18));//te mueve a la poscion encontrada
                mMap.addMarker(new MarkerOptions().position(c));//añade un marke a la posicion en la
                //que GoogleMaps te ha ubicado
                eteDireccion.getText().clear();//limpia el campo de direccion

                //Darle click al marker para redireccionar a la pagina de registro de veterinaria


            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        //agregar tu ubicacion actual
        mMap.setMyLocationEnabled(true);

        //mostrar ubicacion de la vet seleccionada
        Intent i = getIntent();
        i.getExtras();
        double lat = i.getDoubleExtra("latitud", -12.0553017);
        double lon = i.getDoubleExtra("longitud", -77.0626949);
        String nom = i.getStringExtra("nombre");
        LatLng l = new LatLng(lat, lon);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l, 14));
        mMap.addMarker(new MarkerOptions().position(l).title(nom));


        //jalar datos y guardarlo en parse
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                Intent intent = getIntent();
                intent.putExtra("latitud", latLng.latitude);
                intent.putExtra("longitud", latLng.longitude);

                setResult(RESULT_OK, intent);
                onBackPressed();
            }
        });

    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                Toast.makeText(MapsActivity.this, "No se encontro su direccion", Toast.LENGTH_LONG).show();
                p1 = new LatLng(0, 0);
                return p1;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }
}