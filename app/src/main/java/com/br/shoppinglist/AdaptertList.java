package com.br.shoppinglist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptertList extends RecyclerView.Adapter<AdaptertList.ShoppingViewHolder> {
    private List<ItemLista> shoppingList;
    private OnDeleteClickListener onDeleteClickListener;

    public AdaptertList() {
    }

    public AdaptertList(List<ItemLista> repositoryList) {
        this.shoppingList = repositoryList;
    }

    @NonNull @Override public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_list, parent, false);
        return new ShoppingViewHolder(loadingView);
    }

    @Override public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override public int getItemCount() {
        return shoppingList.size();
    }

    public void add(List<ItemLista> repositories) {
        shoppingList.clear();
        shoppingList.addAll(repositories);
        notifyDataSetChanged();
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {
        public TextView txItem;
        public Button btnDeletar;

        public ShoppingViewHolder(View view) {
            super(view);
            txItem = (TextView) view.findViewById(R.id.txtItensId);
            btnDeletar = (Button) view.findViewById(R.id.btnDeletarId);
        }

        public void onBind(int position) {
            ItemLista itemLista = shoppingList.get(position);
            txItem.setText(itemLista.getItem());

            btnDeletar.setOnClickListener(v -> onDeleteClickListener.onClick(itemLista));
        }
    }

    public interface OnDeleteClickListener {
        void onClick(ItemLista itemLista);
    }
}