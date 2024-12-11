package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        Button btnSolicitar;
        ImageView ImgView_pizza;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome_pizza = itemView.findViewById(R.id.txtNome_pizza);
            txtIngredientes = itemView.findViewById(R.id.txtIngredientes);
            txtValor = itemView.findViewById(R.id.txtValor);
            btnSolicitar = itemView.findViewById(R.id.btnSolicitar);
            ImgView_pizza = itemView.findViewById(R.id.ImgView_pizza);
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
        holder.btnSolicitar.setText("Solicitar");
        holder.ImgView_pizza.setImageResource(pizzaModel.getImgPizzaResource());
    }

    @Override
    public int getItemCount() {
        return pizzaModels.size();
    }


}
