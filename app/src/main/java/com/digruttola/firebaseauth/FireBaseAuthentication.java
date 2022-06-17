package com.digruttola.firebaseauth;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthentication {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void registrarse(Context content, String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(content,"Se registro correctamente",Toast.LENGTH_SHORT);
                    }else
                        Toast.makeText(content,"Error al Registrarse",Toast.LENGTH_SHORT);
                }
            });
    }

    public void iniciarSesion(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                    } else {
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                    }
                }
            });
    }

}
