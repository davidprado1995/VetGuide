package com.example.user.vetguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;


import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button FbLoginBtn;
    Button registrar;
    Button login;
    boolean miniparse=true;
    EditText usernameI, passwordI;

    // Your Facebook APP ID
    private static String APP_ID = "1146785745385769"; // Replace your App ID here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Add your initialization code here


        setContentView(R.layout.activity_main);

        FbLoginBtn = (Button) findViewById(R.id.butLoginFacebook);
        registrar = (Button) findViewById(R.id.butRegistrar);
        login = (Button)findViewById(R.id.butLogin);

        FbLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> permissions = Arrays.asList("public_profile", "email");
                ParseFacebookUtils.logInWithReadPermissionsInBackground(MainActivity.this, permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        if (err == null) {

                            if (user == null) {
                                Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                                Toast t=Toast.makeText(MainActivity.this,"Usuario cancelado",Toast.LENGTH_LONG);
                                t.show();

                            } else if (user.isNew()) {
                                //si el usuario es nuevo
                                Log.d("MyApp", "User signed up and logged in through Facebook!");
                                //Utils.parseUser = user;
                                Toast t=Toast.makeText(MainActivity.this,"Usuario logeado",Toast.LENGTH_LONG);
                                t.show();
                                //me redirige aqui---------------------------->
                                Intent intent = new Intent(MainActivity.this, RegistroMascotaActivity.class);
                                startActivity(intent);
                                //Utils.user.setFacebook(true);

                            } else {
                                //si el usuario ya estaba registrado
                                Log.d("MyApp", "User logged in through Facebook!");

                                //me redirige aqui---------------------------->
                                Intent intent = new Intent(MainActivity.this, Inicio.class);
                                intent.putExtra("idUsuario", user.getObjectId());
                                startActivity(intent);
                                Toast t = Toast.makeText(MainActivity.this, "Login de Fb correcto", Toast.LENGTH_LONG);
                                t.show();
                                //Utils.user.setFacebook(true);
                            }
                        } else {
                            Log.e("ErrorFB", err.getMessage().toString());
                            Toast t = Toast.makeText(MainActivity.this, err.toString(), Toast.LENGTH_LONG);
                            t.show();
                        }


                    }
                });
            }
        });


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                usernameI = (EditText)findViewById(R.id.eteUsername);
                passwordI = (EditText)findViewById(R.id.etePassword);

                String u = usernameI.getText().toString();
                String p = passwordI.getText().toString();

                ParseUser currentuser = ParseUser.getCurrentUser();

                currentuser.logOut();



                final ParseUser usuarioentrante = new ParseUser();
                usuarioentrante.setPassword(passwordI.getText().toString());
                usuarioentrante.setEmail(usernameI.getText().toString());


                usuarioentrante.logInInBackground(u, p, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e==null){
                            /*Toast t = Toast.makeText(getBaseContext(),"Login Correcto",Toast.LENGTH_SHORT);
                            t.show();*/

                            Intent i = new Intent(MainActivity.this,Inicio.class);
                            i.putExtra("idUsuario", user.getObjectId());
                            startActivity(i);
                        } else {
                            Toast t = Toast.makeText(getBaseContext(),"Login Incorrecto",Toast.LENGTH_SHORT);
                            t.show();
                        }
                    }
                });

            }
        });


    }

    public void clickRegistrar(View v){
        Intent i = new Intent(MainActivity.this,RegistroActivity.class);
        startActivity(i);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);


}
}

