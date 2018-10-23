package com.iservice.opet.iservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iservice.opet.iservice.DAO.Usuario;
import com.iservice.opet.iservice.activity.CadastrarUsuarioActivity;
import com.iservice.opet.iservice.activity.DadosCadastraisActivity;
import com.iservice.opet.iservice.activity.HomeUsuarioActivity;
import com.iservice.opet.iservice.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ProgressBar progressDB;
    private static final int RC_SIGN_IN = 123;
    private FirebaseUser fbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        progressDB = findViewById(R.id.progressDB);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("carros");
        fbUser = FirebaseAuth.getInstance().getCurrentUser();

        if(fbUser != null) {
            Intent intent = new Intent(this, HomeUsuarioActivity.class);
            startActivity(intent);
        }
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

    public void cadastrarUsuario(Usuario usuario){
        progressDB.setVisibility(ProgressBar.VISIBLE);
        DatabaseReference usuariosRef = mDatabase.getReference("usuarios");
        fbUser = FirebaseAuth.getInstance().getCurrentUser();
        usuariosRef.child(fbUser.getUid()).setValue(usuario).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDB.setVisibility(ProgressBar.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Usuario Salvo", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeUsuarioActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void signIn(View view) {
        startActivityForResult(
            // Get an instance of AuthUI based on the default app
            AuthUI.getInstance().createSignInIntentBuilder().build(),RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {

                /**Instanciara um usuario da model para cadastrar os dados do usuario recem autenticado
                *  na base de dados realtime
                 **/
                Usuario usuarioCriado = new Usuario();
                usuarioCriado.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                usuarioCriado.setNome(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                cadastrarUsuario(usuarioCriado);

            } else {
                // Sign in failed, check response for error code
                if (response != null) {
                    Toast.makeText(this, response.getError().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
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
