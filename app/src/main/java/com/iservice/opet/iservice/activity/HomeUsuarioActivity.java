package com.iservice.opet.iservice.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    //TODO
    public void redirecionarCadastrarServico(View v){
       // Intent intent = new Intent(HomeUsuarioActivity.this,CadastrarUsuarioActivity.class);
      //  startActivity(intent);
    }
}
