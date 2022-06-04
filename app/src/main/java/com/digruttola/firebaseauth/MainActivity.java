package com.digruttola.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        btRegistrarse = findViewById(R.id.bt_main_registrarse);
        btLogIn = findViewById(R.id.bt_main_iniciarSesion);
        editEmail = findViewById(R.id.editText_main_email);
        editPassword = findViewById(R.id.editText_main_password);

        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editPassword.getText().toString().equals("") && !editPassword.getText().toString().equals("")){
                    mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                        Log.d("TAG", "createUserWithEmail:success");
                                    else
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());

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
            Toast.makeText(this,"Ya estas registrado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No estas registrado",Toast.LENGTH_SHORT).show();
        }
    }
}