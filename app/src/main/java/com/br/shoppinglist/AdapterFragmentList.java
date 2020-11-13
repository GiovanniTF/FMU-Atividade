package com.br.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterFragmentList extends BaseAdapter {
    private Context context;
    private List<ItemLista> list;

    public AdapterFragmentList(Context context, List<ItemLista> list) {
        this.context = context;
        this.list = list;
    }

    @Override public int getCount() {
        return list.size();
    }

    @Override public Object getItem(int position) {
        return list.get(position);
    }

    @Override public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override public View getView(int position, View arg1, ViewGroup arg2) {
        final int auxPosition = position;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_adapter_list, null);

        TextView textView = (TextView) layout.findViewById(R.id.txtItensId);
        textView.setText(list.get(position).getItem());

        Button deletarBtn = (Button) layout.findViewById(R.id.btnDeletarId);
        deletarBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                BancoDados bancoDados = new BancoDados(context);
                bancoDados.deletar(list.get(auxPosition));

                layout.setVisibility(View.GONE);
            }
        });

        return layout;
    }
}