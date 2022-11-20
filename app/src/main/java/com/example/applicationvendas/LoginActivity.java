package com.example.applicationvendas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_cpf, edit_passwd;
    private AppCompatButton btn_entrar;
    private TextView text_tela_cadastro;
    Connection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        edit_cpf = (EditText) findViewById(R.id.edit_cpf);
        edit_passwd = (EditText) findViewById(R.id.edit_passwd);
        btn_entrar = (AppCompatButton) findViewById(R.id.btn_entrar);
        text_tela_cadastro = (TextView) findViewById(R.id.text_tela_cadastro);
        db = new Connection(this);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = edit_cpf.getText().toString();
                String passwd = edit_passwd.getText().toString();

                if(user.equals("") || passwd.equals(""))
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUserPasswd = db.checkUsernamePasswd(user, passwd);
                    if(checkUserPasswd == true){
                        Toast.makeText(LoginActivity.this, "Login feito com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), FuncionarioHomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login falhou! Tente novamente", Toast.LENGTH_SHORT  ).show();
                    }
                }
            }
        });


        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadGeralActivity.class);
                startActivity(intent);
            }
        });
    }

}