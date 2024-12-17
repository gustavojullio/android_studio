package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PizzaAdapter  extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {
    ArrayList<PizzaModel> pizzaModels;

    public PizzaAdapter(ArrayList<PizzaModel> pizzaModels){
        this.pizzaModels = pizzaModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNome_pizza;
        TextView txtIngredientes;
        TextView txtValor;
        ImageView ImgView_pizza;
        EditText edtQtd;
        Button btnAdicionar;
        Button btnRemover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome_pizza = itemView.findViewById(R.id.txtNome_pizza);
            txtIngredientes = itemView.findViewById(R.id.txtIngredientes);
            txtValor = itemView.findViewById(R.id.txtValor);
            ImgView_pizza = itemView.findViewById(R.id.ImgView_pizza);
            btnAdicionar = itemView.findViewById(R.id.btnAdicionar);
            btnRemover = itemView.findViewById(R.id.btnRemover);
            edtQtd = itemView.findViewById(R.id.edtQtd);
        }
    }

    //Cria uma nova instância de ViewHolder para cada item da lista.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false );
        return new ViewHolder(view);
    }

    // Exibe cada item da lista
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PizzaModel pizzaModel = pizzaModels.get(position);
        holder.txtNome_pizza.setText("Nome: " + pizzaModel.getNomePizza());
        holder.txtIngredientes.setText("Ingredientes: " + pizzaModel.getIngredientesPizza());
        holder.txtValor.setText("Valor: R$" + pizzaModel.getValorPizza());
        holder.ImgView_pizza.setImageResource(pizzaModel.getImgPizzaResource());
        holder.edtQtd.setText(String.valueOf(pizzaModel.getQuantidade()));

        // Listener para adicionar
        holder.btnAdicionar.setOnClickListener(v -> {
            int novaQuantidade = pizzaModel.getQuantidade() + 1;
            pizzaModel.setQuantidade(novaQuantidade);
            holder.edtQtd.setText(String.valueOf(novaQuantidade));
        });

        // Listener para remover
        holder.btnRemover.setOnClickListener(v -> {
            int novaQuantidade = Math.max(pizzaModel.getQuantidade() - 1, 0);
            pizzaModel.setQuantidade(novaQuantidade);
            holder.edtQtd.setText(String.valueOf(novaQuantidade));
        });
    }

    @Override
    public int getItemCount() {
        return pizzaModels.size();
    }


}
