package com.iservice.opet.iservice.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iservice.opet.iservice.R;

import java.util.HashMap;

public class DadosCadastraisActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private EditText userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cadastrais);
        mDatabase = FirebaseDatabase.getInstance();
        userEmail = findViewById(R.id.editTextEmail);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usuarioRef = mDatabase.getReference("usuarios" +"/" + user.getUid());
      //  userEmail.setText(usuarioRef.child("email").toString());

        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String,String> resultUser = (HashMap<String, String>) dataSnapshot.getValue();


                //TODO Percorrer o hashmap de resultado de dados do usuario para apresentar no front-end
                // da aplicacao
                //for(String result : resultUser){

                //}

                userEmail.setText(dataSnapshot.getValue().toString());
                System.out.println("debug");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
