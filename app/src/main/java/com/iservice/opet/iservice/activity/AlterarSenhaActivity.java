package com.iservice.opet.iservice.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iservice.opet.iservice.MainActivity;
import com.iservice.opet.iservice.R;

public class AlterarSenhaActivity extends AppCompatActivity {

    EditText editSenha;
    FirebaseAuth auth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha);
        editSenha = (EditText) findViewById(R.id.editTextSenha);
        auth = FirebaseAuth.getInstance();
    }

    public void alterarSenha(View v) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.updatePassword(editSenha.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Senha alterada com sucesso!", Toast.LENGTH_SHORT);
                        auth.signOut();
                        finish();
                        Intent i = new Intent(AlterarSenhaActivity.this,MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "Problemas ao alterar senha!", Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }
}
