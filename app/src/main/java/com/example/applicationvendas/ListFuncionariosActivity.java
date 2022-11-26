package com.example.applicationvendas;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListFuncionariosActivity extends AppCompatActivity {

    private EditText edit_nome, edit_cpf, edit_salario, edit_cargo;
    private FuncionarioDAO dao;
    private Connection db;
    private Funcionario funcionario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_funcionarios);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_cpf = (EditText) findViewById(R.id.edit_cpf);
        edit_salario = (EditText) findViewById(R.id.edit_salario);
        edit_cargo = (EditText) findViewById(R.id.edit_cargo);
        db = new Connection(this);
        dao = new FuncionarioDAO(this);


        Intent intent = getIntent();
        if(intent.hasExtra("funcionario")){
            funcionario = (Funcionario) intent.getSerializableExtra("funcionario");
            edit_nome.setText(funcionario.getNome());
            edit_cpf.setText(funcionario.getCpf());
            edit_salario.setText(String.valueOf(funcionario.getSalario()));
            edit_cargo.setText(funcionario.getCargo());
        }

    }
    public void btn_cadastrar(View view) {
        if (funcionario == null) {
            Funcionario f = new Funcionario();
            funcionario.setNome(edit_nome.getText().toString());
            funcionario.setCpf(edit_cpf.getText().toString());
            funcionario.setCargo(edit_cargo.getText().toString());
            funcionario.setSalario(((Float.parseFloat(edit_salario.getText().toString()))));
            long id = dao.insert(f);
            Toast.makeText(this, "Funcionáiro cadastrado com o ID: " + id, Toast.LENGTH_LONG).show();
        } else {
            funcionario.setNome(edit_nome.getText().toString());
            funcionario.setCpf(edit_cpf.getText().toString());
            funcionario .setCargo(edit_cargo.getText().toString());
            funcionario.setSalario(((Float.parseFloat(edit_salario.getText().toString()))));
            dao.updateF(funcionario);
            Toast.makeText(this, "Funcionáiro atualizado", Toast.LENGTH_LONG).show();
        }
    }
    public void btn_tela_login(View view){
        Intent intent = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
        startActivity(intent);
    }

}