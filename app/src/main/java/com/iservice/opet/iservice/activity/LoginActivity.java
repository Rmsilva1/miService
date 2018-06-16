package com.iservice.opet.iservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.iservice.opet.iservice.DAO.Usuario;
import com.iservice.opet.iservice.DAO.UsuarioDAO;
import com.iservice.opet.iservice.MainActivity;
import com.iservice.opet.iservice.R;

public class LoginActivity extends Activity {

    private EditText edtEmail;
    private EditText edtPassword;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = (EditText) findViewById(R.id.etEmail);
        edtPassword = (EditText) findViewById(R.id.etSenha);
        info = (TextView) findViewById(R.id.tvInfo);
    }

    public void validarLogin(View v){
        String email = edtEmail.getText().toString();
        String senha = edtPassword.getText().toString();

        if("".equals(email)) {
            exibirMensagem("Campo email é obrigatorio!!");
            return;
        }
        if("".equals(senha)){
            exibirMensagem("Campo senha é obrigatorio!!");
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Boolean validado =  usuarioDAO.validaLoginUsuario(email,senha);

        if(validado){
            Intent intent = new Intent(LoginActivity.this, HomeUsuarioActivity.class);
            startActivity(intent);
            finish();
        }else{
            exibirMensagem("Erro ao realizar login, Email ou senha Invalidos");
        }
    }

    public void redirecionarMainActivity(View v){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
