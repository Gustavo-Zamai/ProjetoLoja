package com.example.applicationvendas;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private List<Produto> produtos;
    private Activity activity;

    public ProdutoAdapter(Activity activity, List<Produto> produtos){
        this.activity = activity;
        this.produtos = produtos;
    }
    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return produtos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_produtos, viewGroup, false);
        TextView nome_produto = v.findViewById(R.id.nome_produto);
        TextView categoria_produto = v.findViewById(R.id.categoria_produto);
        TextView descricao_produto = v.findViewById(R.id.descricao_produto);
        TextView preco_produto = v.findViewById(R.id.preco_produto);
        TextView qtd_produtos = v.findViewById(R.id.qtd_produtos);
        Produto p = produtos.get(i);
        nome_produto.setText(p.getNome_produto());
        categoria_produto.setText(p.getCategoria());
        descricao_produto.setText(p.getDescricao());
        preco_produto.setText(String.valueOf(p.getPreco()));
        qtd_produtos.setText(String.valueOf(p.getQuantidade()));
        return v;
    }
}
