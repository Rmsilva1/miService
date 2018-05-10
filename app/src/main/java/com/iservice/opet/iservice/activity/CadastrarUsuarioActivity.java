package com.iservice.opet.iservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iservice.opet.iservice.DAO.UsuarioDAO;
import com.iservice.opet.iservice.DAO.Usuario;
import com.iservice.opet.iservice.MainActivity;
import com.iservice.opet.iservice.R;

/**
 * Created by Rafael on 25/04/2018.
 */
public class CadastrarUsuarioActivity extends Activity {
    private EditText editNome;
    private EditText editCpf;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editSenhaConfirma;
    private EditText editTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        editNome = (EditText) findViewById(R.id.editNome);
        editCpf = (EditText) findViewById(R.id.editCpf);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        editSenhaConfirma = (EditText) findViewById(R.id.editSenhaConfirma);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
    }

    public void cadastrarUsuario(View v){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Usuario usuario = new Usuario();
        usuario.setNome(editNome.getText().toString());
        usuario.setCpf(editCpf.getText().toString());
        usuario.setEmail(editEmail.getText().toString());
        usuario.setSenha(editSenha.getText().toString());
        usuario.setTelefone(editTelefone.getText().toString());

        if(!editSenha.getText().toString().equals(editSenhaConfirma.getText().toString())){
            exibirMensagem("As senhas devem ser iguais!!.");
        }else{
            long resultado = usuarioDAO.cadastrarUsuario(usuario);
            if(resultado > 0){
                exibirMensagem("Usuario cadastrado com sucesso!");
                Intent intent = new Intent(CadastrarUsuarioActivity.this, HomeUsuarioActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                exibirMensagem("Erro ao cadastrar o usuario!.");
            }
        }
    }
    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}