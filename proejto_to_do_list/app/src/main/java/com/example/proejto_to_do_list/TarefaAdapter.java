package com.example.proejto_to_do_list;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    ArrayList<TarefaModel> tarefaModels;

    public TarefaAdapter(ArrayList<TarefaModel> tarefaModels){
        this.tarefaModels = tarefaModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNome_Tarefa;
        CheckBox checkBoxTarefa;
        Boolean realizada;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome_Tarefa = itemView.findViewById(R.id.txtNome_Tarefa);
            checkBoxTarefa = itemView.findViewById(R.id.checkBoxTarefa);

        }
    }
    //Cria uma nova instância de ViewHolder para cada item da lista.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false );
        return new ViewHolder(view);
    }

    //Cria uma nova instância de ViewHolder para cada item da lista.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TarefaModel tarefaModel = tarefaModels.get(position);

        if (tarefaModel.isRealizada()) {
            tarefaModel.setRealizada(true);
            holder.checkBoxTarefa.setChecked(true);
            holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            tarefaModel.setRealizada(false);
            holder.checkBoxTarefa.setChecked(false);
            holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.txtNome_Tarefa.setText(tarefaModel.getNomeTarefa());

        holder.checkBoxTarefa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });





    }

    @Override
    public int getItemCount() {
        return tarefaModels.size();
    }


}
