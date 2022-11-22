package com.example.applicationvendas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private Connection connection;
    private SQLiteDatabase db;

    public FornecedorDAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    public long  insertFornecedor (Fornecedor fornecedor){
        ContentValues values = new ContentValues();
        values.put("nome", fornecedor.getNome());
        values.put("cnpj", fornecedor.getCnpj());
        values.put("email", fornecedor.getEmail());
        values.put("telefone", fornecedor.getTelefone());
        return db.insert("fornecedor", null, values);
    }

    public List<Fornecedor> getFornecedor(){
        List<Fornecedor> fornecedor = new ArrayList<>();
        Cursor cursor;
        cursor = db.query("fornecedor", new String[]{"id", "nome", "cnpj", "email", "telefone"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Fornecedor f = new Fornecedor();
            f.setId(cursor.getInt(0));
            f.setNome(cursor.getString(1));
            f.setCnpj(cursor.getString(2));
            f.setEmail(cursor.getString(3));
            f.setTelefone(cursor.getString(4));
            fornecedor.add(f);
        }
        return fornecedor;
    }

    public void deleteFornecedor (Fornecedor fornecedor){//para excluir da tabela Funcionario, onde o id for o que vier do db
        db.delete("fornecedor", "id = ?", new String[]{String.valueOf(fornecedor.getId())});
    }

    public void updateFornecedor (Fornecedor fornecedor){
        ContentValues values = new ContentValues();
        values.put("nome", fornecedor.getNome());
        values.put("cnpj", fornecedor.getCnpj());
        values.put("email", fornecedor.getEmail());
        values.put("telefone", fornecedor.getTelefone());
        db.update("fornecedor", values, "id = ?", new String[]{String.valueOf(fornecedor.getId())});
    }
}
