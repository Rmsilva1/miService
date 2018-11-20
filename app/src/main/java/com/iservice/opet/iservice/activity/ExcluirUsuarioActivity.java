package com.iservice.opet.iservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iservice.opet.iservice.MainActivity;
import com.iservice.opet.iservice.R;

public class ExcluirUsuarioActivity extends Activity {
    private FirebaseUser fbUser;
    private FirebaseAuth auth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_meu_usuario);
        mDatabase = FirebaseDatabase.getInstance();
        fbUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void deletarUsuario(View v){
        DatabaseReference usuarioRef = mDatabase.getReference("usuarios" +"/" + fbUser.getUid());
        usuarioRef.removeValue();
        fbUser.delete();
        redirecionarLogin();
    }

    public void redirecionarHomeUsuario(View v){
        Intent intent = new Intent(ExcluirUsuarioActivity.this, HomeUsuarioActivity.class);
        startActivity(intent);
    }

    public void redirecionarLogin(){
        Intent intent = new Intent(ExcluirUsuarioActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
