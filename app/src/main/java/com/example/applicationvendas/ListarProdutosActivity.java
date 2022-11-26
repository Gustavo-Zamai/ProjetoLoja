package com.example.applicationvendas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    private ListView listView;
    private ProdutoDAO dao;
    private List<Produto> produtos;
    private List<Produto> produtosConsulta = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        listView = findViewById(R.id.lista_produtos);
        dao = new ProdutoDAO(this);
        produtos = dao.getProdutos();
        produtosConsulta.addAll(produtos); //add todos os fncionarios na tabela func consulta
        ProdutoAdapter adapt = new ProdutoAdapter(this, produtosConsulta); //joga para a view na tela
        listView.setAdapter(adapt);//coloca a lista na tela atraves do adaptador

        registerForContextMenu(listView);//jogar os registros de menu, para o list view
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater x = getMenuInflater();
        x.inflate(R.menu.menu_principal, menu); //coloca o menu dentro da tela listar

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();//pegar a busca do nome feita pelo user
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraProduto(s);//Verificar se foi feita pesquisa
                return false;
            }
        });
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);//contexto para receber os dados do menu
        MenuInflater j = getMenuInflater();
        j.inflate(R.menu.menu_exclucao, menu);//joga informações em outro menu, no caso o de exclucao
    }

    public void delete(MenuItem item){//pegar a posicao do item na lsita
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();//jogando o menu para o adaptador

        final Produto produtoDelete = produtosConsulta.get(menuInfo.position);//pega o aluno com a posicao

        AlertDialog dialog = new AlertDialog.Builder(this)//msg de confirmacao para o usuario
                .setTitle("Atenção")
                .setMessage("Confirma a exclução do Produto? ")
                .setNegativeButton("Não",null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {//se for sim exclui das 2 listas
                        produtosConsulta.remove(produtoDelete);//e do db o selecionado
                        produtos.remove(produtoDelete);
                        dao.delete(produtoDelete);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void procuraProduto(String nome){
        produtosConsulta.clear();
        for (Produto p : produtos){
            if(p.getNome_produto().toLowerCase().contains(nome.toLowerCase())){
                produtosConsulta.add(p);
            }
        }
        listView.invalidateViews();
    }


    public void cadastrar(MenuItem item){//vinculado com o XML do menu_principal
        Intent i = new Intent(this, CadProdutosActivity.class);
        startActivity(i);
    }

    public void update(MenuItem menuItem){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        final Produto produtoUpdate = produtosConsulta.get(menuInfo.position);
        Intent intent = new Intent(this, CadProdutosActivity.class);
        intent.putExtra("produto", produtoUpdate);
        startActivity(intent);
    }

    public void back (MenuItem menuItem){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
        //final Funcionario funcionarioUpdate = funcionarioConsulta.get(menuInfo.position);
        Intent intent = new Intent(this, FuncionarioHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume(){//atualiza a lista na tela do celular
        super.onResume();
        produtos = dao.getProdutos();
        produtosConsulta.addAll(produtos);
        listView.invalidateViews();//para exibir os novos itens/modificados
    }
    }
