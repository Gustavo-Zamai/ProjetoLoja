package com.example.applicationvendas;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FuncionarioAdapter extends BaseAdapter {

    private List<Funcionario> funcionarios;
    private Activity activity;

    public FuncionarioAdapter(Activity activity, List<Funcionario> funcionarios){
        this.activity = activity;
        this.funcionarios = funcionarios;
    }
    @Override
    public int getCount() {
        return funcionarios.size();
    }

    @Override
    public Object getItem(int i) {
        return funcionarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return funcionarios.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_funcionario, viewGroup, false);
        TextView nome_funcionario = v.findViewById(R.id.nome_funcionario);
        TextView cargo_funcionario = v.findViewById(R.id.cargo_funcionario);
        TextView cpf_funcionario = v.findViewById(R.id.cpf_funcionario);
        TextView salario_funcionario = v.findViewById(R.id.qtd_produtos);
        Funcionario f = funcionarios.get(i);
        nome_funcionario.setText(f.getNome());
        cargo_funcionario.setText(f.getCargo());
        cpf_funcionario.setText(f.getCpf());
        salario_funcionario.setText(String.valueOf(f.getSalario()));
        return v;
    }
}
