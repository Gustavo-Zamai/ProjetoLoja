package com.example.applicationvendas;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class FornecedorAdapter extends BaseAdapter {

    private List<Fornecedor> fornecedor;
    private Activity activity;

    public FornecedorAdapter(Activity activity, List<Fornecedor> fornecedor){
        this.activity = activity;
        this.fornecedor = fornecedor;
    }

    @Override
    public int getCount() {
        return fornecedor.size();
    }

    @Override
    public Object getItem(int i) {
        return fornecedor.get(i);
    }

    @Override
    public long getItemId(int i) {
        return fornecedor.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_fornecedor, viewGroup, false);
        TextView nome_fornecedor = v.findViewById(R.id.nome_fornecedor);
        TextView cnpj = v.findViewById(R.id.cnpj);
        TextView email = v.findViewById(R.id.email);
        TextView telefone = v.findViewById(R.id.telefone);
        Fornecedor f = fornecedor.get(i);
        nome_fornecedor.setText(f.getNome());
        cnpj.setText(f.getCnpj());
        email.setText(f.getEmail());
        telefone.setText(f.getEmail());
        return v;
    }
}
