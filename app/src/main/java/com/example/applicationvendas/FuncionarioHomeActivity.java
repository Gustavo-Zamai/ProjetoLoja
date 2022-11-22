package com.example.applicationvendas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FuncionarioHomeActivity extends AppCompatActivity {

    private CardView lista_produtos_disponiveis, add_produtos, lista_fornecedores, add_fornecedor, lista_funcionarios, sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario_home);
        getSupportActionBar().hide();

        lista_produtos_disponiveis = (CardView) findViewById(R.id.lista_produtos_disponiveis);
        add_produtos = (CardView) findViewById(R.id.add_produtos);
        lista_fornecedores = (CardView) findViewById(R.id.lista_fornecedores);
        add_fornecedor = (CardView) findViewById(R.id.add_fornecedor);
        lista_funcionarios = (CardView) findViewById(R.id.lista_funcionarios);
        sair = (CardView) findViewById(R.id.sair);

    }
    public void lista_produtos_disponiveis(View v){
        Intent intent = new Intent(getApplicationContext(), ListarProdutosActivity.class);
        startActivity(intent);
    }
    public void add_produtos(View v){
        Intent intent = new Intent(getApplicationContext(), CadProdutosActivity.class);
        startActivity(intent);
    }
    public void lista_fornecedores(View v){
        Intent intent = new Intent(getApplicationContext(), ListarFornecedorActivity.class);
        startActivity(intent);
    }
    public void add_fornecedor(View v){
        Intent intent = new Intent(getApplicationContext(), CadFornnecedorActivity.class);
        startActivity(intent);
    }
    public void lista_funcionarios(View v){
        Intent intent = new Intent(getApplicationContext(), ListarFuncionariosActivity.class);
        startActivity(intent);
    }
    public void sair(View v){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}