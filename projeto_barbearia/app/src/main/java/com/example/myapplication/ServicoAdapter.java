package com.example.myapplication;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ViewHolder> {
    ArrayList<ServicoModel> servicoModels;
    private int selectedPosition = -1; // Nenhum item selecionado inicialmente

    public ServicoAdapter(ArrayList<ServicoModel> servicoModels) {
        this.servicoModels = servicoModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomeServico;
        ImageView imgServico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeServico = itemView.findViewById(R.id.txtNomeServico);
            imgServico = itemView.findViewById(R.id.imgServico);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ServicoModel servicoModel = servicoModels.get(position);
        holder.imgServico.setImageResource(servicoModel.getImgServicoResource());
        holder.txtNomeServico.setText(servicoModel.getNomeServico());

        // Atualizar a aparência do item para indicar se ele está selecionado
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFB400")); // Cor de destaque
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#232323")); // Cor normal
        }

        // Adicionando o clique para selecionar o item
        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position; // Marca o item como selecionado
            notifyDataSetChanged(); // Atualiza a visualização do RecyclerView
        });
    }

    @Override
    public int getItemCount() {
        return servicoModels.size();
    }

    // Método para obter a posição do item selecionado
    public int getSelectedPosition() {
        return selectedPosition;
    }
}
