package com.br.shoppinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String BD_ITENS = "BDItens";
    private static final int VERSAO_BD = 7;

    public BDCore(Context context) {
        super(context, BD_ITENS, null, VERSAO_BD);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tabelaItem(_id integer primary key autoincrement, item text not null);");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table tabelaItem;");
        onCreate(db);
    }
}
