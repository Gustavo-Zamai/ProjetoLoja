package com.example.applicationvendas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection connection;
    private SQLiteDatabase db;

    public FuncionarioDAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    public long  insert(Funcionario funcionario){
        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("cpf", funcionario.getCpf());
        values.put("cargo", funcionario.getCargo());
        values.put("salario", funcionario.getSalario());
        values.put("senha",funcionario.getSenha());
        return db.insert("funcionario", null, values);
    }

    public List<Funcionario> getFuncionarios(){
        List<Funcionario> func = new ArrayList<>();
        Cursor cursor;
        cursor = db.query("funcionario", new String[]{"id", "nome", "cpf", "cargo", "salario" ,"senha"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Funcionario funcionario = new Funcionario();
            funcionario.setId(cursor.getInt(0));
            funcionario.setNome(cursor.getString(1));
            funcionario.setCpf(cursor.getString(2));
            funcionario.setCargo(cursor.getString(3));
            funcionario.setSalario(cursor.getFloat(4));
            funcionario.setSenha(cursor.getString(5));
            func.add(funcionario);
        }
        return func;
    }

    public void delete(Funcionario f){//para excluir da tabela Funcionario, onde o id for o que vier do db
        db.delete("funcionario", "id = ?", new String[]{String.valueOf(f.getId())});
    }

    public void updateF(Funcionario funcionario){
        ContentValues values = new ContentValues();
        values.put("nome", funcionario.getNome());
        values.put("cpf", funcionario.getCpf());
        values.put("cargo", funcionario.getCargo());
        values.put("salario", funcionario.getSalario());
        values.put("senha",funcionario.getSenha());
        db.update("funcionario", values, "id = ?", new String[]{String.valueOf(funcionario.getId())});
    }

}
