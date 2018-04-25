package com.iservice.opet.iservice.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iservice.opet.iservice.Factory.DatabaseFactory;
import com.iservice.opet.iservice.Util.BancoUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Rafael on 25/04/2018.
 * Classe para inserção, remoção, atualização e busca em Banco de Dados de um Usuario.
 */

public class UsuarioDAO {
    private SQLiteDatabase db;
    private DatabaseFactory banco;


    public UsuarioDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long cadastrarUsuario(Usuario usuario) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.USUARIO_IS_TECNICO, usuario.getTecnico());
        valores.put(BancoUtil.USUARIOS_NOME, usuario.getNome());
        valores.put(BancoUtil.USUARIOS_CPF, usuario.getCpf());
        valores.put(BancoUtil.USUARIOS_EMAIL, usuario.getEmail());
        valores.put(BancoUtil.USUARIOS_SENHA, usuario.getSenha());
        valores.put(BancoUtil.USUARIOS_TELEFONE, usuario.getTelefone());

        resultado = db.insert(BancoUtil.NOME_TABELA_USUARIOS, null, valores);
        db.close();

        return resultado;
    }

    public Usuario consultaUsuarioPorId(Integer id){
        Cursor cursor;
        String[] campos = {BancoUtil.USUARIO_ID, BancoUtil.USUARIOS_NOME, BancoUtil.USUARIOS_EMAIL, BancoUtil.USUARIOS_CPF};
        db = banco.getReadableDatabase();

        String where = BancoUtil.USUARIO_ID + " = " + id;

        cursor = db.query(BancoUtil.NOME_TABELA_USUARIOS, campos, where, null, null, null, null, null);

        Usuario usuario = new Usuario();
        if (cursor != null) {
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.USUARIO_ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.USUARIOS_NOME));
            String cpf = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.USUARIOS_CPF));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.USUARIOS_EMAIL));

            usuario.setId(ID);
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
        }
        db.close();
        return usuario;
    }

    public Cursor carregaDados() {
        Cursor cursor;

        String[] campos = {BancoUtil.USUARIO_ID, BancoUtil.USUARIOS_NOME, String.valueOf(BancoUtil.VERSAO)};
        db = banco.getReadableDatabase();

        //String where = BancoUtil.LIVRO_USUARIO + " = " + id_usuario;
        String where = "";

        cursor = db.query(BancoUtil.NOME_TABELA_USUARIOS, campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public List<Usuario> carregaDadosLista() {
        Cursor cursor;
        cursor = carregaDados();

        List<Usuario> usuarios = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Usuario usuario = new Usuario();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.USUARIO_ID));
                    String nome = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.USUARIOS_NOME));
                    String cpf = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.USUARIOS_CPF));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.USUARIOS_EMAIL));

                    usuario.setId(ID);
                    usuario.setNome(nome);
                    usuario.setCpf(cpf);
                    usuario.setEmail(email);

                    usuarios.add(usuario);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return usuarios;
    }

    public void deletaUsuarioPorId(int id){
        String where = BancoUtil.USUARIO_ID + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.NOME_TABELA_USUARIOS,where,null);
        db.close();
    }

    public boolean atualizaUsuario(Usuario usuario){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = BancoUtil.USUARIO_ID + " = " + usuario.getId();

        valores = new ContentValues();
        valores.put(BancoUtil.USUARIOS_NOME, usuario.getNome());
        valores.put(BancoUtil.USUARIOS_CPF, usuario.getCpf());


        int resultado = db.update(BancoUtil.NOME_TABELA_USUARIOS,valores,where,null);
        db.close();
        if(resultado > 0)
            return true;
        else
            return false;
    }
}