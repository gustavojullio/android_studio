package com.example.proejto_to_do_list;
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
    // Declaração dos componentes da interface
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

        // Inicializa o adapter com a lista de tarefas
        adapter = new TarefaAdapter(tarefaModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    // Método responsável por adicionar uma nova tarefa ao clicar no botão "Adicionar"
    public void adicionar(View view) {

        // Verifica se o campo de texto não está vazio
        if (!edtTarefa.getText().toString().isEmpty()){
            // Adiciona uma nova tarefa
            tarefaModels.add(new TarefaModel(edtTarefa.getText().toString(), false));
            adapter.notifyItemInserted(tarefaModels.size()-1);

            // Limpa o campo de texto para uma nova tarefa
            edtTarefa.setText("");
        }else {
            Toast.makeText(this, "Digite uma tarefa!!", Toast.LENGTH_SHORT).show();
        }
    }

}