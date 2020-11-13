package com.br.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private ItemLista itemLista = new ItemLista();
    private EditText item;
    private Button adicionar;
    private RecyclerView recyclerView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = (EditText) findViewById(R.id.edtItemId);
        adicionar = (Button) findViewById(R.id.btnAdicionarId);
        recyclerView = (RecyclerView) findViewById(R.id.rcvItensId);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                itemLista.setId(bundle.getLong("id"));
                itemLista.setItem(bundle.getString("item"));

                item.setText(itemLista.getItem());
            }
        }

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                salvarItem();
            }
        });
    }

    public void salvarItem() {
        itemLista.setItem(item.getText().toString());

        BancoDados bancoDados = new BancoDados(this);
        bancoDados.inserir(itemLista);

        Toast.makeText(this, "Item Inserido com Sucesso!", Toast.LENGTH_LONG).show();
    }
}