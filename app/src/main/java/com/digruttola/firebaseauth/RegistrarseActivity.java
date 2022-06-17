package com.digruttola.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText editEmail,editPassword,editNombre,editEdad;
    private Button btRegistrarse,btVolver;

    private FireBaseCloud cloud = new FireBaseCloud();
    private FireBaseAuthentication auth = new FireBaseAuthentication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        editEmail       = findViewById(R.id.editText_register_email);
        editPassword    = findViewById(R.id.editText_register_password);
        editNombre      = findViewById(R.id.editText_register_nombre);
        editEdad        = findViewById(R.id.editText_register_edad);
        btRegistrarse   = findViewById(R.id.bt_register_registrarse);
        btVolver        = findViewById(R.id.bt_register_volver);

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyEditTexts()){
                    String email = editEmail.getText().toString();
                    String password = editPassword.getText().toString();
                    String name = editNombre.getText().toString();
                    String edad = editEdad.getText().toString();

                    auth.registrarse(RegistrarseActivity.this,email,password,name,edad);
                }
            }
        });

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrarseActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private boolean verifyEditTexts(){
        return  !editEmail.getText().toString().equals("") &&
                !editPassword.getText().toString().equals("") &&
                !editNombre.getText().toString().equals("") &&
                !editEdad.getText().toString().equals("");
    }
     void cleanEditTexs(){
        editEmail.setText("");
        editPassword.setText("");
        editNombre.setText("");
        editEdad.setText("");
    }
}