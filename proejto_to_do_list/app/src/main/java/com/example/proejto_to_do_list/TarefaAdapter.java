package com.example.proejto_to_do_list;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.EditText;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolder> {
    // Lista de tarefas que será exibida no RecyclerView
    ArrayList<TarefaModel> tarefaModels;

    public TarefaAdapter(ArrayList<TarefaModel> tarefaModels){
        this.tarefaModels = tarefaModels;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNome_Tarefa;
        CheckBox checkBoxTarefa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa os componentes do item
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
        // Obtém a tarefa atual
        TarefaModel tarefaModel = tarefaModels.get(position);

        // Verifica o status da tarefa para estilizar o texto e o checkbox
        if (tarefaModel.isRealizada()) {
            tarefaModel.setRealizada(true);
            holder.checkBoxTarefa.setChecked(true);
            holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            tarefaModel.setRealizada(false);
            holder.checkBoxTarefa.setChecked(false);
            holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        // Define o texto do nome da tarefa
        holder.txtNome_Tarefa.setText(tarefaModel.getNomeTarefa());

        // Listener para marcar ou desmarcar uma tarefa como concluída
        holder.checkBoxTarefa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.checkBoxTarefa.isChecked()){
                    tarefaModel.setRealizada(true);
                    holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else{
                    holder.checkBoxTarefa.setChecked(false);
                    tarefaModel.setRealizada(false);
                    holder.txtNome_Tarefa.setPaintFlags(holder.txtNome_Tarefa.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }

            }
        });

        // Listener para clique curto no nome da tarefa (editar tarefa)
        holder.txtNome_Tarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Editar Tarefa");

                final EditText input = new EditText(v.getContext());
                input.setText(tarefaModel.getNomeTarefa());
                builder.setView(input);

                builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String novaTarefa = input.getText().toString();
                        tarefaModel.setNomeTarefa(novaTarefa);
                        holder.txtNome_Tarefa.setText(novaTarefa);
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        // Listener para clique longo no nome da tarefa (excluir tarefa)
        holder.txtNome_Tarefa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Excluir Tarefa");
                builder.setMessage("Tem certeza que deseja excluir esta tarefa?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Remove a tarefa da lista
                        tarefaModels.remove(position);
                        // Notifica o adapter sobre a remoção
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, tarefaModels.size());
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                return true;
            }
        });
    }

    // Retorna o número de itens na lista
    @Override
    public int getItemCount() {
        return tarefaModels.size();
    }

}
