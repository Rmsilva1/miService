package com.iservice.opet.iservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.iservice.opet.iservice.MainActivity;
import com.iservice.opet.iservice.R;

/**
 * Created by Rafael on 09/05/2018.
 */
public class HomeUsuarioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_usuario);
    }

    public void logOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomeUsuarioActivity.this, "Deslogando!", Toast.LENGTH_SHORT).show();
        redirecionarMainActivity(view);
    }

    //TODO
    public void redirecionarCadastrarServico(View v){
       // Intent intent = new Intent(HomeUsuarioActivity.this,CadastrarUsuarioActivity.class);
      //  startActivity(intent);
    }

    public void redirecionarEditarMeuUsuario(View v){
        Intent intent = new Intent(HomeUsuarioActivity.this, EditarMeuUsuario.class);
        startActivity(intent);
    }

    public void redirecionarMainActivity(View v){
        Intent intent = new Intent(HomeUsuarioActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void redirecionarListarUsuarios(View v){
        Intent intent = new Intent(HomeUsuarioActivity.this, ListarTodosUsuariosActivity.class);
        startActivity(intent);
    }

    public void redirecionarAlterarSenha(View v){
        Intent intent = new Intent(HomeUsuarioActivity.this, AlterarSenhaActivity.  class);
        startActivity(intent);
    }

    public void redirecionarDadosCadastraisActivity(View v){
        Intent intent = new Intent(HomeUsuarioActivity.this, DadosCadastraisActivity.class);
        startActivity(intent);
    }
}
