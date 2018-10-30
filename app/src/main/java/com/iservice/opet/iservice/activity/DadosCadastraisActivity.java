package com.iservice.opet.iservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iservice.opet.iservice.DAO.Usuario;
import com.iservice.opet.iservice.MainActivity;
import com.iservice.opet.iservice.R;

import java.util.HashMap;

public class DadosCadastraisActivity extends AppCompatActivity {
    private ProgressBar progressDB;
    private FirebaseAuth auth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private EditText userEmail;
    private TextView userName;
    private EditText userTel;
    private EditText userCpf;
    private FirebaseUser fbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cadastrais);
        mDatabase = FirebaseDatabase.getInstance();
        userEmail = findViewById(R.id.editTextEmail);
        userName = findViewById(R.id.editTextUserName);
        userTel = findViewById(R.id.editTextUserTel);
        userCpf  = findViewById(R.id.editTextUserCpf);
        progressDB = findViewById(R.id.progressDB);
        mDatabase = FirebaseDatabase.getInstance();
        fbUser = FirebaseAuth.getInstance().getCurrentUser();

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usuarioRef = mDatabase.getReference("usuarios" +"/" + user.getUid());
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String,String> resultUser = (HashMap<String, String>) dataSnapshot.getValue();
                userEmail.setText(resultUser.get("email"));
                userName.setText(resultUser.get("nome"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void editarUsuario(View view){
        Usuario usuario = new Usuario();
        usuario.setEmail(userEmail.getText().toString());
        usuario.setTelefone(userTel.getText().toString());
        usuario.setCpf(userCpf.getText().toString());
        salvarEdicao(usuario);
    }

    public void salvarEdicao(Usuario usuario){
        DatabaseReference usuarioRef = mDatabase.getReference("usuarios");
        fbUser = FirebaseAuth.getInstance().getCurrentUser();
        usuarioRef.child(fbUser.getUid()).setValue(usuario).addOnCompleteListener(DadosCadastraisActivity.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DadosCadastraisActivity.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DadosCadastraisActivity.this, HomeUsuarioActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(DadosCadastraisActivity.this, "Erro ao salvar alterações", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
