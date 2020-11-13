package com.br.shoppinglist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ItemLista itemLista;
    private AdaptertList adaptertList;
    private EditText item;
    private Button adicionar;
    private RecyclerView recyclerView;
    private BancoDados bancoDados;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.edtItemId);
        adicionar = (Button) findViewById(R.id.btnAdicionarId);
        recyclerView = (RecyclerView) findViewById(R.id.rcvItensId);

        bancoDados = new BancoDados(this);

        adaptertList = new AdaptertList(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptertList);

        if(bancoDados.buscar().size() > 0){
            adaptertList.add(bancoDados.buscar());
        }

        adicionar.setOnClickListener(v -> salvarItem());

        adaptertList.setOnDeleteClickListener(itemLista -> {
            bancoDados.deletar(itemLista);
            adaptertList.add(bancoDados.buscar());
        });
    }

    public void salvarItem() {
        itemLista = new ItemLista();
        itemLista.setItem(item.getText().toString());
        bancoDados.inserir(itemLista);
        adaptertList.add(bancoDados.buscar());

        Toast.makeText(this, R.string.itemInserido, Toast.LENGTH_LONG).show();
    }
}