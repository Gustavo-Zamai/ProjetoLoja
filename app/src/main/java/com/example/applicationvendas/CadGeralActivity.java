package com.example.applicationvendas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadGeralActivity extends AppCompatActivity {

    private EditText edit_nome, edit_cpf, edit_salario, edit_passwd_cadastro,edit_passwd_cadastro_2, edit_cargo;
    private FuncionarioDAO dao;
    private Connection db;
    private Funcionario funcionario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadgeral);
        getSupportActionBar().hide();

        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_cpf = (EditText) findViewById(R.id.edit_cpf);
        edit_salario = (EditText) findViewById(R.id.edit_salario);
        edit_passwd_cadastro = (EditText) findViewById(R.id.edit_passwd_cadastro);
        edit_passwd_cadastro_2 = (EditText) findViewById(R.id.edit_passwd_cadastro_2);
        edit_cargo = (EditText) findViewById(R.id.edit_cargo);
        db = new Connection(this);
        dao = new FuncionarioDAO(this);


        Intent intent = getIntent();
        if(intent.hasExtra("funcionario")){
            funcionario = (Funcionario) intent.getSerializableExtra("funcionario");
            edit_nome.setText(funcionario.getNome());
            edit_cpf.setText(funcionario.getCpf());
            edit_salario.setText(String.valueOf(funcionario.getSalario()));
            edit_passwd_cadastro.setText(funcionario.getSenha());
            edit_cargo.setText(funcionario.getCargo());
        }

    }
        public void btn_cadastrar(View view) {
                Funcionario f = new Funcionario();
                f.setNome(edit_nome.getText().toString());
                f.setCpf(edit_cpf.getText().toString());
                f.setCargo(edit_cargo.getText().toString());
                f.setSalario(((Float.parseFloat(edit_salario.getText().toString()))));
                f.setSenha(edit_passwd_cadastro.getText().toString());
                long id = dao.insert(f);
                Toast.makeText(this, "Funcionáiro cadastrado com o ID: " + id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
        }
        public void btn_tela_login(View view){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }


}
