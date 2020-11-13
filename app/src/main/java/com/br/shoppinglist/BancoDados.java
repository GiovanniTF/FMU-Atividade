package com.br.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    private SQLiteDatabase db;

    public BancoDados(Context context) {
        BDCore auxBancoDados = new BDCore(context);
        db = auxBancoDados.getWritableDatabase();
    }

    public void inserir(ItemLista itemLista) {
        ContentValues valores = new ContentValues();
        valores.put("item", itemLista.getItem());

        db.insert("tabelaItem", null, valores);
    }

    public void atualizar(ItemLista itemLista) {
        ContentValues valores = new ContentValues();
        valores.put("item", itemLista.getItem());

        db.update("tabelaItem", valores, "_id = ?", new String[]{"" + itemLista.getId()});
    }

    public void deletar(ItemLista itemLista) {
        db.delete("tabelaItem", "_id = " + itemLista.getId(), null);
    }

    public void cleanList(ItemLista itemLista){
        db.delete("tabelaItem", null, null);
    }

    public List<ItemLista> buscar() {
        List<ItemLista> list = new ArrayList<>();
        String[] colunas = new String[]{"_id", "item"};

        Cursor cursor = db.query("tabelaItem", colunas, null, null, null, null, "_id");

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ItemLista itemLista = new ItemLista();
                itemLista.setId(cursor.getLong(0));
                itemLista.setItem(cursor.getString(1));
                list.add(itemLista);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
