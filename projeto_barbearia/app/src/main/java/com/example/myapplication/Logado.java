package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Logado extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ServicoModel> servicoModels = new ArrayList<>();
    ServicoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        // Inicialização do RecyclerView
        recyclerView = findViewById(R.id.recyclerview);

        // Criando os modelos de serviços
        ServicoModel servico1 = new ServicoModel("Corte de Cabelo", R.drawable.tesoura);
        ServicoModel servico2 = new ServicoModel("Corte de Barba", R.drawable.barba);
        ServicoModel servico3 = new ServicoModel("Lavagem de Cabelo", R.drawable.lavagem);
        ServicoModel servico4 = new ServicoModel("Tratamento de Cabelo", R.drawable.tratamento);

        // Adicionando os serviços no ArrayList
        servicoModels.add(servico1);
        servicoModels.add(servico2);
        servicoModels.add(servico3);
        servicoModels.add(servico4);

        // Criando o adapter
        adapter = new ServicoAdapter(servicoModels);

        // Usando o GridLayoutManager para exibir 2 itens por linha
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2); // 2 colunas
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        // Configurando o botão de Agendar
        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            int selectedPosition = adapter.getSelectedPosition();
            if (selectedPosition == -1) {
                // Nenhum serviço selecionado, exibe o Toast
                Toast.makeText(this, "Por favor, adicione um serviço", Toast.LENGTH_SHORT).show();
            } else {
                // Serviço selecionado, exibe a ação a ser realizada
                String nomeServico = servicoModels.get(selectedPosition).getNomeServico();

                Intent intent = new Intent(Logado.this, Agendamento.class);
                intent.putExtra("nomeServico", nomeServico);

                // Inicia a próxima Activity
                startActivity(intent);
            }
        });
    }
}
