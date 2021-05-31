package com.br.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ItemLista itemLista;
    private AdaptertList adaptertList;
    private EditText item;
    private Button adicionar;
    private Button limparLista;
    private RecyclerView recyclerView;
    private BancoDados bancoDados;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        item = (EditText) findViewById(R.id.edtItemId);
        adicionar = (Button) findViewById(R.id.btnAdicionarId);
        limparLista = (Button) findViewById(R.id.btnLimparId);
        recyclerView = (RecyclerView) findViewById(R.id.rcvItensId);

        bancoDados = new BancoDados(this);

        adaptertList = new AdaptertList(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptertList);

        if (bancoDados.buscar().size() > 0) {
            adaptertList.add(bancoDados.buscar());
        }

        adicionar.setOnClickListener(v -> salvarItem());

        adaptertList.setOnDeleteClickListener(itemLista -> {
            bancoDados.deletar(itemLista);
            adaptertList.add(bancoDados.buscar());
            Toast.makeText(this, R.string.mensagem_item_deletado, Toast.LENGTH_SHORT).show();
        });

        limparLista.setOnClickListener(v -> {
            bancoDados.cleanList(itemLista);
            adaptertList.add(bancoDados.buscar());
            Toast.makeText(this, R.string.mensagem_lista_limpa, Toast.LENGTH_SHORT).show();
        });
    }

    public void salvarItem() {
        itemLista = new ItemLista();
        itemLista.setItem(item.getText().toString());
        bancoDados.inserir(itemLista);
        adaptertList.add(bancoDados.buscar());
        item.getText().clear();

        Toast.makeText(this, R.string.itemInserido, Toast.LENGTH_SHORT).show();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_menu:
                Intent  i = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}