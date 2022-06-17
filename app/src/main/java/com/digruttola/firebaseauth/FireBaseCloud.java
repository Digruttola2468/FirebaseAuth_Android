package com.digruttola.firebaseauth;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FireBaseCloud {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void agregar(String id,String email,String username,String edad){
        Map<String,String> mapeo = new HashMap();
        mapeo.put("email",email);
        mapeo.put("username",username);
        mapeo.put("edad",edad);

        db.collection("User").document(id).set(mapeo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d("FIREBASECLOUD","Agregado con exito");
                }
                else {
                    Log.w("FIREBASECLOUD","Error al agregar");
                }
            }
        });
    }

    public void buscarPorUID(String UID){
        db.collection("User").document(UID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

    }
    public void buscarPorUID(String UID, TextView email,TextView nombre, TextView edad){
        db.collection("User").document(UID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG", "DocumentSnapshot data: " + document.getData());
                        email.setText(document.getData().get("email").toString());
                        nombre.setText(document.getData().get("username").toString());
                        edad.setText(document.getData().get("edad").toString());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

    }

}

