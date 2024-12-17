package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<PizzaModel> pizzaModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialização do RecyclerView
        this.recyclerView = findViewById(R.id.recyclerview);


        // Instanciação dos objetos para o RecyclerView
        PizzaModel pizza1 = new PizzaModel("Margherita", "Mussarela, tomate, manjericão", 45.00, R.drawable.pizza_margherita);
        PizzaModel pizza2 = new PizzaModel("Calabresa", "Calabresa, cebola, azeitona", 50.00, R.drawable.pizza_calabresa);
        PizzaModel pizza3 = new PizzaModel("Quatro Queijos", "Mussarela, gorgonzola, parmesão, provolone", 55.00, R.drawable.pizza_quatro_queijos);
        PizzaModel pizza4 = new PizzaModel("Frango com Catupiry", "Frango desfiado, catupiry, milho", 52.00, R.drawable.pizza_frango_catupiry);
        PizzaModel pizza5 = new PizzaModel("Portuguesa", "Presunto, ovos, azeitona, cebola, tomate", 58.00, R.drawable.pizza_portuguesa);
        PizzaModel pizza6 = new PizzaModel("Pepperoni", "Pepperoni, queijo mussarela, tomate", 60.00, R.drawable.pizza_pepperoni);
        PizzaModel pizza7 = new PizzaModel("Vegetariana", "Cogumelos, pimentão, cebola, tomate, abobrinha", 48.00, R.drawable.pizza_vegetariana);
        PizzaModel pizza8 = new PizzaModel("Napolitana", "Mussarela, molho de tomate, manjericão", 46.00, R.drawable.pizza_napolitana);
        PizzaModel pizza9 = new PizzaModel("Mexicana", "Carne moída, pimentão, cebola, cheddar", 62.00, R.drawable.pizza_mexicana);
        PizzaModel pizza10 = new PizzaModel("Hawaiana", "Presunto, abacaxi, queijo", 53.00, R.drawable.pizza_hawaiana);
        PizzaModel pizza11 = new PizzaModel("Bacon", "Bacon crocante, queijo, molho especial", 65.00, R.drawable.pizza_bacon);
        PizzaModel pizza12 = new PizzaModel("Mussarela", "Queijo mussarela, molho de tomate", 40.00, R.drawable.pizza_mussarela);
        PizzaModel pizza13 = new PizzaModel("Pesto", "Molho pesto, mussarela de búfala, tomate", 57.00, R.drawable.pizza_pesto);
        PizzaModel pizza14 = new PizzaModel("Rúcula com Parmesão", "Rúcula fresca, queijo parmesão, tomate seco", 59.00, R.drawable.pizza_rucula_parmesao);

        // Inclusão dos objetos instanciados no ArrayList
        pizzaModels.add(pizza1);
        pizzaModels.add(pizza2);
        pizzaModels.add(pizza3);
        pizzaModels.add(pizza4);
        pizzaModels.add(pizza5);
        pizzaModels.add(pizza6);
        pizzaModels.add(pizza7);
        pizzaModels.add(pizza8);
        pizzaModels.add(pizza9);
        pizzaModels.add(pizza10);
        pizzaModels.add(pizza11);
        pizzaModels.add(pizza12);
        pizzaModels.add(pizza13);
        pizzaModels.add(pizza14);

        RecyclerView.Adapter<PizzaAdapter.ViewHolder> adapter = new PizzaAdapter(pizzaModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /*
    *
    * */
    public void solicitar(View view) {
        int totalQuantidade = 0;
        double totalValor = 0.0;
        StringBuilder resumoPedido = new StringBuilder();

        // percorre a lista de pizzas e soma as quantidades e valores
        for (PizzaModel pizza : pizzaModels) {
            if (pizza.getQuantidade() > 0) { // somente exibir pizzas com quantidade maior que 0
                totalQuantidade += pizza.getQuantidade();
                double valorPizza = pizza.getQuantidade() * pizza.getValorPizza();
                totalValor += valorPizza;

                // adiciona detalhes da pizza no resumo
                resumoPedido.append("Pizza: ").append(pizza.getNomePizza())
                        .append("\nQuantidade: ").append(pizza.getQuantidade())
                        .append("\nSubtotal: R$ ").append(String.format("%.2f", valorPizza))
                        .append("\n\n");
            }
        }

        // verifica se alguma pizza foi selecionada
        if (totalQuantidade == 0) {
            resumoPedido.append("Nenhuma pizza foi selecionada.");
        }
        else {
            resumoPedido.append("-------------------------\n");
            resumoPedido.append("Total de Pizzas: ").append(totalQuantidade).append("\n");
            resumoPedido.append("Valor Total: R$ ").append(String.format("%.2f", totalValor)).append("\n\n");
            resumoPedido.append("Obrigado pela preferência!");
        }

        // exibe o AlertDialog com os detalhes do pedido
        new AlertDialog.Builder(this)
                .setTitle("Resumo do Pedido")
                .setMessage(resumoPedido.toString())
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // fecha o dialog
                    }
                })
                .setCancelable(false) // evita que o usuário feche clicando fora do dialog
                .show();
    }

}