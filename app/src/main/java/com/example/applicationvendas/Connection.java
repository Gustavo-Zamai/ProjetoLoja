package com.example.applicationvendas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper{
    private static final String name = "banco.db";
    private static final int version = 1;

    public Connection(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table funcionario(id integer primary key autoincrement," +
                "nome varchar(25) not null, cpf varchar (25) not null, senha varchar(25) not null," +
                "salario float not null, cargo varchar (25))");
        db.execSQL("create table produto(id integer primary key autoincrement," +
                "nome varchar(25) not null, categoria varchar(25), preco float not null, descricao varchar (50)" +
                ", quantidade integer not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists funcionario");
        db.execSQL("drop table if exists produto");
    }

   public Boolean checkUsername(String cpf) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from funcionario where cpf = ?", new String[]{cpf});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePasswd(String cpf, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from funcionario where cpf = ? and senha = ?",
                new String[]{cpf, senha});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
