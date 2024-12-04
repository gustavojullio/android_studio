package com.example.recycle_view_app;

import android.os.Bundle;
import android.view.View;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.recyclerview);

        RocketModel rocketModel_1 = new RocketModel("falcon 1", "06/11/2024", true, "satelite", R.drawable.img1);
        RocketModel rocketModel_2 = new RocketModel("atlas v", "12/12/2024", true, "exploração", R.drawable.img2);
        RocketModel rocketModel_3 = new RocketModel("delta iv", "15/01/2025", false, "observação", R.drawable.img1);
        RocketModel rocketModel_4 = new RocketModel("ares i", "20/02/2025", true, "experimento", R.drawable.img2);
        RocketModel rocketModel_5 = new RocketModel("eagle", "05/03/2025", true, "exploração lunar", R.drawable.img1);
        RocketModel rocketModel_6 = new RocketModel("saturn v", "17/04/2025", false, "missão tripulada", R.drawable.img2);
        RocketModel rocketModel_7 = new RocketModel("titan iv", "22/05/2025", true, "satélite de comunicação", R.drawable.img1);
        RocketModel rocketModel_8 = new RocketModel("space shuttle", "30/06/2025", true, "exploração espacial", R.drawable.img2);
        RocketModel rocketModel_9 = new RocketModel("pegasus", "10/07/2025", false, "carga útil pequena", R.drawable.img1);
        RocketModel rocketModel_10 = new RocketModel("soyuz", "18/08/2025", true, "missão tripulada", R.drawable.img2);

        ArrayList<RocketModel> rocketModels = new ArrayList<>();
        rocketModels.add(rocketModel_1);
        rocketModels.add(rocketModel_2);
        rocketModels.add(rocketModel_3);
        rocketModels.add(rocketModel_4);
        rocketModels.add(rocketModel_5);
        rocketModels.add(rocketModel_6);
        rocketModels.add(rocketModel_7);
        rocketModels.add(rocketModel_8);
        rocketModels.add(rocketModel_9);
        rocketModels.add(rocketModel_10);

        RecyclerView.Adapter<RocketAdapter.ViewHolder> adapter = new RocketAdapter(rocketModels);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}