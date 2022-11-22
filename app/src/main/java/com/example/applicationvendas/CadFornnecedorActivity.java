package com.example.applicationvendas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CadFornnecedorActivity extends AppCompatActivity {
    private EditText edit_nome, edit_cnpj, edit_email, edit_telefone;
    private AppCompatButton btn_add_fornecedor, btn_voltar_home;
    private FornecedorDAO dao;
    private Connection db;
    private Fornecedor fornecedor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_fornnecedor);
        getSupportActionBar().hide();

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_cnpj = (EditText) findViewById(R.id.edit_cnpj);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_telefone = (EditText) findViewById(R.id.edit_telefone);
        btn_add_fornecedor = (AppCompatButton) findViewById(R.id.btn_add_fornecedor);
        btn_voltar_home = (AppCompatButton) findViewById(R.id.btn_voltar_home);
        dao = new FornecedorDAO(this);
        db = new Connection(this);

        Intent intent = getIntent();
        if (intent.hasExtra("fornecedor")) {
            fornecedor = (Fornecedor) intent.getSerializableExtra("fornecedor");
            edit_nome.setText(fornecedor.getNome());
            edit_cnpj.setText((fornecedor.getCnpj()));
            edit_email.setText(fornecedor.getEmail());
            edit_telefone.setText(fornecedor.getTelefone());
        }

    }

    public void btn_add_fornecedor (View view) {
        if (fornecedor == null) {
            Fornecedor f = new Fornecedor();
            f.setNome(edit_nome.getText().toString());
            f.setCnpj(edit_cnpj.getText().toString());
            f.setEmail(edit_email.getText().toString());
            f.setTelefone(edit_telefone.getText().toString());
            long id = dao.insertFornecedor(f);
            Toast.makeText(this, "Fornecedor cadastrado com o ID: " + id, Toast.LENGTH_SHORT).show();
        } else {
            fornecedor.setNome(edit_nome.getText().toString());
            fornecedor.setCnpj(edit_cnpj.getText().toString());
            fornecedor.setEmail(edit_email.getText().toString());
            fornecedor.setTelefone(edit_telefone.getText().toString());
            dao.updateFornecedor(fornecedor);
            Toast.makeText(this, "Fornecedor atualizado",Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_voltar_home (View v){
        Intent intent = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
        startActivity(intent);
    }
}