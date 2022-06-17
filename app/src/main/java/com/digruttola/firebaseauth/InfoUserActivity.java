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
    private TextView viewEmail,viewNombre,viewTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        btSignOut = findViewById(R.id.bt_userInfo_cerrarSesion);
        viewEmail = findViewById(R.id.txt_email);
        viewNombre = findViewById(R.id.txt_nombre);
        viewTelefono = findViewById(R.id.txt_telefono);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        viewEmail.setText(user.getEmail());
        viewNombre.setText(user.getDisplayName());
        viewTelefono.setText(user.getPhoneNumber());

        Log.d("",user.getDisplayName() + user.getEmail() + user.getPhoneNumber());

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