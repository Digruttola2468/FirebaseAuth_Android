package com.digruttola.firebaseauth;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthFireBase {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void registrarse(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
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

