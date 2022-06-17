package com.digruttola.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btRegistrarse,btLogIn;
    private EditText editEmail,editPassword;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail       = findViewById(R.id.editText_main_email);
        editPassword    = findViewById(R.id.editText_main_password);
        btRegistrarse   = findViewById(R.id.bt_main_registrarse);
        btLogIn         = findViewById(R.id.bt_main_iniciarSesion);


        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrarseActivity.class);
                startActivity(i);
            }
        });

        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyEditTexts()){
                    mAuth.signInWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(MainActivity.this, InfoUserActivity.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(MainActivity.this,"No existe",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(this, InfoUserActivity.class);
            startActivity(i);
        }
    }

    private boolean verifyEditTexts(){
        return !editPassword.getText().toString().equals("") && !editPassword.getText().toString().equals("");
    }
}