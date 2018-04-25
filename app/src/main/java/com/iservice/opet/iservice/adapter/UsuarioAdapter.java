package com.iservice.opet.iservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iservice.opet.iservice.DAO.Usuario;
import com.iservice.opet.iservice.R;

import org.w3c.dom.Text;

import java.util.List;

//import android.support.annotation.LayoutRes;
//import android.support.annotation.NonNull;


/**
 * Created by Rafael on 25/04/2018.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private int resource;
    private List<Usuario> usuarios;

    public UsuarioAdapter(Context context, int resource, List<Usuario> objects) {
        super(context, resource, objects);
        this.resource = resource;
        usuarios = objects;
    }
    /*public SistemaAdapter(Context context, @LayoutRes int resource, List<Sistema> objects) {
        super(context, resource, objects);
        this.resource = resource;
        sistemas = objects;
    }
*/
    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Usuario usuario = usuarios.get(position);

      /*  TextView textID_usuario = (TextView) mView.findViewById(R.id.textID);
        TextView textIsTecnico = (TextView) mView.findViewById(R.id.textNome);
        TextView textNome = (TextView) mView.findViewById(R.id.textVersao);
        TextView textCpf = (TextView) mView.findViewById(R.id)
        TextView textEmail;
        TextView textSenha;
        TextView textTelefone;
        */



       /* if(textID != null){
            textID.setText(String.valueOf(sistema.getID()));
        }
        if(textTitulo != null){
            textTitulo.setText(sistema.getNome());
        }
        if(textGenero != null){
            textGenero.setText(sistema.getVersao());
        }*/
        return mView;
    }
}
