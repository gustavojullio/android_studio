package com.example.projeto_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Logado extends AppCompatActivity {
    TextView txtLogado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.logado);

        txtLogado = findViewById(R.id.txtLogado);

        Intent intent = getIntent();
        String nomeUsuario = intent.getStringExtra("nomeUsuario");

        txtLogado.setText("Seja bem-vindo " + nomeUsuario + "!");
    }
}