package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnSolicitar;
    Button btnAdicionar;
    Button btnRemover;
    private static int qtd = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialização do RecyclerView
        this.recyclerView = findViewById(R.id.recyclerview);


        // Instanciação dos objetos para o RecyclerView

        PizzaModel pizza1 = new PizzaModel("Margherita", "Mussarela, tomate, manjericão", 45.00, btnSolicitar, R.drawable.pizza_margherita);
        PizzaModel pizza2 = new PizzaModel("Calabresa", "Calabresa, cebola, azeitona", 50.00, btnSolicitar, R.drawable.pizza_calabresa);
        PizzaModel pizza3 = new PizzaModel("Quatro Queijos", "Mussarela, gorgonzola, parmesão, provolone", 55.00, btnSolicitar, R.drawable.pizza_quatro_queijos);
        PizzaModel pizza4 = new PizzaModel("Frango com Catupiry", "Frango desfiado, catupiry, milho", 52.00, btnSolicitar, R.drawable.pizza_frango_catupiry);
        PizzaModel pizza5 = new PizzaModel("Portuguesa", "Presunto, ovos, azeitona, cebola, tomate", 58.00, btnSolicitar, R.drawable.pizza_portuguesa);
        PizzaModel pizza6 = new PizzaModel("Pepperoni", "Pepperoni, queijo mussarela, tomate", 60.00, btnSolicitar, R.drawable.pizza_pepperoni);
        PizzaModel pizza7 = new PizzaModel("Vegetariana", "Cogumelos, pimentão, cebola, tomate, abobrinha", 48.00, btnSolicitar, R.drawable.pizza_vegetariana);
        PizzaModel pizza8 = new PizzaModel("Napolitana", "Mussarela, molho de tomate, manjericão", 46.00, btnSolicitar, R.drawable.pizza_napolitana);
        PizzaModel pizza9 = new PizzaModel("Mexicana", "Carne moída, pimentão, cebola, cheddar", 62.00, btnSolicitar, R.drawable.pizza_mexicana);
        PizzaModel pizza10 = new PizzaModel("Hawaiana", "Presunto, abacaxi, queijo", 53.00, btnSolicitar, R.drawable.pizza_hawaiana);
        PizzaModel pizza11 = new PizzaModel("Bacon", "Bacon crocante, queijo, molho especial", 65.00, btnSolicitar, R.drawable.pizza_bacon);
        PizzaModel pizza12 = new PizzaModel("Mussarela", "Queijo mussarela, molho de tomate", 40.00, btnSolicitar, R.drawable.pizza_mussarela);
        PizzaModel pizza13 = new PizzaModel("Pesto", "Molho pesto, mussarela de búfala, tomate", 57.00, btnSolicitar, R.drawable.pizza_pesto);
        PizzaModel pizza14 = new PizzaModel("Rúcula com Parmesão", "Rúcula fresca, queijo parmesão, tomate seco", 59.00, btnSolicitar, R.drawable.pizza_rucula_parmesao);

        ArrayList<PizzaModel> pizzaModels = new ArrayList<>();
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

    // método para solicitar a pizza

    public void solicitar(View view){
        Button button = (Button) view;
        button.setBackgroundColor(0xFF28A745);

    }

    public void remover(View view){
        Button button = (Button) view;
        button.setBackgroundColor(0xFF28A745);

    }

    public void adicionar(View view){
        Button button = (Button) view;
        button.setBackgroundColor(0xFF28A745);

    }
}