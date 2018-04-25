package com.iservice.opet.iservice.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iservice.opet.iservice.Util.BancoUtil;


/**
 * Created by Rafael on 25/04/2018.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ BancoUtil.NOME_TABELA_USUARIOS + "("
                + BancoUtil.USUARIO_ID + " integer primary key autoincrement,"
                + BancoUtil.USUARIO_IS_TECNICO + " text,"
                + BancoUtil.USUARIOS_NOME + " text,"
                + BancoUtil.USUARIOS_CPF + " text,"
                + BancoUtil.USUARIOS_EMAIL + " text,"
                + BancoUtil.USUARIOS_SENHA + " text,"
                + BancoUtil.USUARIOS_TELEFONE + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.NOME_TABELA_USUARIOS);
        onCreate(db);
    }
}
