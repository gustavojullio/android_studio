package com.example.proejto_to_do_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtTarefa;
    RecyclerView recyclerView;
    ArrayList<TarefaModel> tarefaModels = new ArrayList<>();
    RecyclerView.Adapter<TarefaAdapter.ViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialização do RecyclerView
        this.recyclerView = findViewById(R.id.recyclerview);
        edtTarefa = findViewById(R.id.edtTarefa);

        TarefaModel tarefa1 = new TarefaModel("Tarefa 1", false);
        TarefaModel tarefa2 = new TarefaModel("Tarefa 2", false);
        TarefaModel tarefa3 = new TarefaModel("Tarefa 3", false);
        TarefaModel tarefa4 = new TarefaModel("Tarefa 4", true);


        tarefaModels.add(tarefa1);
        tarefaModels.add(tarefa2);
        tarefaModels.add(tarefa3);
        tarefaModels.add(tarefa4);

        adapter = new TarefaAdapter(tarefaModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void adicionar(View view) {
        if (!edtTarefa.getText().toString().isEmpty()){
            tarefaModels.add(new TarefaModel(edtTarefa.getText().toString(), false));
            adapter.notifyItemInserted(tarefaModels.size()-1);
        }else {
            Toast.makeText(this, "Digite uma tarefa!!", Toast.LENGTH_SHORT).show();
        }
    }

}