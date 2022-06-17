package com.digruttola.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InfoUserActivity extends AppCompatActivity {

    private Button btSignOut;
    private TextView viewEmail,viewNombre, viewEdad;

    private FireBaseCloud cloud = new FireBaseCloud();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        btSignOut = findViewById(R.id.bt_userInfo_cerrarSesion);
        viewEmail = findViewById(R.id.txt_email);
        viewNombre = findViewById(R.id.txt_nombre);
        viewEdad = findViewById(R.id.txt_edad);

        String UID = getIntent().getExtras().get("UID").toString();
        cloud.buscarPorUID(UID,viewEmail,viewNombre,viewEdad);


        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(InfoUserActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}