package com.example.applicationvendas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadFuncionarioLogadoActivity extends AppCompatActivity {
    private EditText edit_nome, edit_cpf, edit_salario, edit_passwd_cadastro, edit_passwd_cadastro_2, edit_cargo;
    private FuncionarioDAO dao;
    private Connection db;
    private Funcionario funcionario = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_funcionario_logado);
        getSupportActionBar().hide();

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_cpf = (EditText) findViewById(R.id.edit_cpf);
        edit_salario = (EditText) findViewById(R.id.edit_salario);
        edit_passwd_cadastro = (EditText) findViewById(R.id.edit_passwd_cadastro);
        edit_passwd_cadastro_2 = (EditText) findViewById(R.id.edit_passwd_cadastro_2);
        edit_cargo = (EditText) findViewById(R.id.edit_cargo);
        db = new Connection(this);
        dao = new FuncionarioDAO(this);
    }
    public void btn_add(View view) {
        if (funcionario == null) {
            Funcionario f = new Funcionario();
            f.setNome(edit_nome.getText().toString());
            f.setCpf(edit_cpf.getText().toString());
            f.setSenha(edit_passwd_cadastro.getText().toString());
            f.setSalario(Float.parseFloat(edit_salario.getText().toString()));
            f.setCargo(edit_cargo.getText().toString());
            long id = dao.insert(f);
            Toast.makeText(this, "Fornecedor cadastrado com o ID: " + id, Toast.LENGTH_SHORT).show();
        } else {
            funcionario.setNome(edit_nome.getText().toString());
            funcionario.setCpf(edit_cpf.getText().toString());
            funcionario.setSenha(edit_passwd_cadastro.getText().toString());
            funcionario.setSalario(Float.parseFloat(edit_salario.getText().toString()));
            funcionario.setCargo(edit_cargo.getText().toString());
            dao.updateF(funcionario);
            Toast.makeText(this, "Fornecedor atualizado",Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
        startActivity(i);
    }
    public void btn_voltar(View view){
        Intent intent = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
        startActivity(intent);
    }

}