package com.iservice.opet.iservice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.iservice.opet.iservice.DAO.Usuario;
import com.iservice.opet.iservice.DAO.UsuarioDAO;
import com.iservice.opet.iservice.R;

import java.util.ArrayList;
import java.util.List;

public class ListarTodosUsuariosActivity extends AppCompatActivity {

    private List<Usuario> usuarios;
    private ListView listView;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        listView = findViewById(R.id.listview);

        arrayList.add("teste");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);
        populateListView();
    }

    public void populateListView(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarios = usuarioDAO.carregaDadosLista();

        ArrayList<String> nomes = new ArrayList<>();

        for(Usuario usuario: usuarios){
            arrayList.add(usuario.getNome());
        }
        adapter.notifyDataSetChanged();
    }
}
