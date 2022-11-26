package com.example.applicationvendas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CadProdutosActivity extends AppCompatActivity {

    private EditText inputProduto, inputCategoria, inputPreco, inputDesc, inputQtd;
    private AppCompatButton btn_add, btn_voltar;
    private ProdutoDAO dao;
    private Connection db;
    private Produto produto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produtos);
        getSupportActionBar().hide();

        inputProduto = (EditText) findViewById(R.id.inputProduto);
        inputCategoria = (EditText) findViewById(R.id.inputCategoria);
        inputPreco = (EditText) findViewById(R.id.inputPreco);
        inputDesc = (EditText) findViewById(R.id.inputDesc);
        inputQtd = (EditText) findViewById(R.id.inputQtd);
        btn_add = (AppCompatButton) findViewById(R.id.btn_add);
        btn_voltar = (AppCompatButton) findViewById(R.id.btn_voltar);
        dao = new ProdutoDAO(this);
        db = new Connection(this);


        Intent intent = getIntent();
        if (intent.hasExtra("produto")) {
            produto = (Produto) intent.getSerializableExtra("produto");
            inputProduto.setText(produto.getNome_produto());
            inputCategoria.setText((produto.getCategoria()));
            inputPreco.setText(String.valueOf(produto.getPreco()));
            inputDesc.setText(produto.getDescricao());
            inputQtd.setText(String.valueOf(produto.getQuantidade()));
        }
    }

    public void btn_add(View view) {
        if (produto == null) {
            Produto p = new Produto();
            p.setNome_produto(inputProduto.getText().toString());
            p.setCategoria(inputCategoria.getText().toString());
            p.setPreco(Float.parseFloat(inputPreco.getText().toString()));
            p.setDescricao(inputDesc.getText().toString());
            p.setQuantidade(Integer.parseInt(inputQtd.getText().toString()));
            long id = dao.insertProduto(p);
            Toast.makeText(this, "Produto cadastrado com o ID: " + id, Toast.LENGTH_LONG).show();
        } else {
            produto.setNome_produto(inputProduto.getText().toString());
            produto.setCategoria(inputCategoria.getText().toString());
            produto.setPreco(Float.parseFloat(inputPreco.getText().toString()));
            produto.setDescricao(inputDesc.getText().toString());
            produto.setQuantidade(Integer.parseInt(inputQtd.getText().toString()));
            dao.updateP(produto);
            Toast.makeText(this, "Produto atualizado", Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
        startActivity(i);
    }

    public void btn_voltar(View v){
        Intent intent = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
        startActivity(intent);
    }
}