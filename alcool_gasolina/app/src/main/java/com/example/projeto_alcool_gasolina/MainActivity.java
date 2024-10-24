package com.example.projeto_alcool_gasolina;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.button);
        EditText gasolina = findViewById(R.id.editTextText);
        EditText alcool = findViewById(R.id.editTextText2);
        TextView resultado = findViewById(R.id.textView2);

        gasolina.addTextChangedListener(new MoneyTextWatcher(gasolina));
        alcool.addTextChangedListener(new MoneyTextWatcher(alcool));

    }
    public void calc(View view){
        EditText gasolina = findViewById(R.id.editTextText);
        EditText alcool = findViewById(R.id.editTextText2);
        TextView resultado = findViewById(R.id.textView2);
        ImageView img = findViewById(R.id.imageView);

        if (!gasolina.getText().toString().equals("") && !alcool.getText().toString().equals("")){
            try {
                String clearStringGasolina = gasolina.getText().toString().replaceAll("[^\\d]", "");
                String clearStringAlcool = alcool.getText().toString().replaceAll("[^\\d]", "");

                double valorGasolina = Double.parseDouble(clearStringGasolina);
                double valorAlcool = Double.parseDouble(clearStringAlcool);

                if (valorAlcool / valorGasolina < 0.7){
                    resultado.setText("Resultado: Abasteça com Álcool.");
                    img.setImageResource(R.drawable.alcool);
                }else{
                    resultado.setText("Resultado: Abasteça com Gasolina.");
                    img.setImageResource(R.drawable.gasolina);
                }

            }catch (Exception e){
                Toast.makeText(this, "Erro ao capturar os valores", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha todos os campos. ", Toast.LENGTH_SHORT).show();
        }






    }
}