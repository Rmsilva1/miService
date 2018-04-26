package com.iservice.opet.iservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.stetho.Stetho;
import com.iservice.opet.iservice.activity.CadastrarUsuarioActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
    }

    public void redirecionarCadastrarUsuario(View v){
        Intent intent = new Intent(MainActivity.this,CadastrarUsuarioActivity.class);
        startActivity(intent);
    }
}
