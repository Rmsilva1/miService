package com.iservice.opet.iservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iservice.opet.iservice.activity.CadastrarUsuarioActivity;
import com.iservice.opet.iservice.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ProgressBar progressDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        progressDB = findViewById(R.id.progressDB);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("carros");
    }

    public void salvarCarro(View view){
        Carro carro = new Carro("Fiat", "Uno", 97);
        progressDB.setVisibility(ProgressBar.VISIBLE);
        mRef.child("carro").setValue(carro).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDB.setVisibility(ProgressBar.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Carro Salvo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void redirecionarCadastroActivty(View v){
        Intent intent = new Intent(MainActivity.this,CadastrarUsuarioActivity.class);
        startActivity(intent);
    }

    public void redirecionarLoginActivity(View v){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
