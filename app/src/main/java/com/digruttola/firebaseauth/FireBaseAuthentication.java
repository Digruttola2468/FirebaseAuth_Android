package com.digruttola.firebaseauth;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthentication {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FireBaseCloud cloud = new FireBaseCloud();

    public void registrarse(Context content, String email, String password,String nombre,String edad){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        cloud.agregar(task.getResult().getUser().getUid(),email,nombre,edad);
                        Log.d("TAG","UID: " + task.getResult().getUser().getUid() + "  email: " + email + "  nombre" + nombre + "   edad: " + edad);
                    }else{
                        Log.w("TAG","Error al agregar usuario");
                    }

                }
            });
    }

    public void iniciarSesion(Context context,String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(context,InfoUserActivity.class);
                        i.putExtra("UID",task.getResult().getUser().getUid());
                        context.startActivity(i);
                    } else {
                        Log.w("TAG", "signInWithEmail:failure", task.getException());
                    }
                }
            });
    }

}
