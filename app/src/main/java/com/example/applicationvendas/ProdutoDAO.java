package com.example.applicationvendas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Connection connection;
    private SQLiteDatabase db;

    public ProdutoDAO(Context context){
        connection = new Connection(context);
        db = connection.getWritableDatabase();
    }

    public long insertProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome_produto());
        values.put("categoria", produto.getCategoria());
        values.put("preco", produto.getPreco());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());
        return db.insert("produto", null, values);
    }

    public List<Produto> getProdutos(){
        List<Produto> p = new ArrayList<>();
        Cursor cursor;
        cursor = db.query("produto", new String[]{"id", "nome", "categoria", "preco", "descricao" ,"quantidade"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setNome_produto(cursor.getString(1));
            produto.setCategoria(cursor.getString(2));
            produto.setPreco(cursor.getFloat(3));
            produto.setDescricao(cursor.getString(4));
            produto.setQuantidade(cursor.getInt(5));
            p.add(produto);
        }
        return p;
    }
    public List<Produto> getProdutosConsulta(){
        List<Produto> p = new ArrayList<>();
        Cursor cursor;
        cursor = db.query("produto", new String[]{"id", "nome", "categoria", "preco", "descricao" ,"quantidade"},
                null, null, null, null, null);
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setId(cursor.getInt(0));
            produto.setNome_produto(cursor.getString(1));
            produto.setCategoria(cursor.getString(2));
            produto.setPreco(cursor.getFloat(3));
            produto.setDescricao(cursor.getString(4));
            produto.setQuantidade(cursor.getInt(5));
            p.add(produto);
        }
        return p;
    }

    public void delete(Produto produto){//para excluir da tabela Produto, onde o id for o que vier do db
        db.delete("produto", "id = ?", new String[]{String.valueOf(produto.getId())});
    }

    public void updateP(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome_produto());
        values.put("categoria", produto.getCategoria());
        values.put("preco", produto.getPreco());
        values.put("descricao", produto.getDescricao());
        values.put("quantidade",produto.getQuantidade());
        db.update("produto",values, "id = ?" , new String[]{String.valueOf(produto.getId())});
    }
}
