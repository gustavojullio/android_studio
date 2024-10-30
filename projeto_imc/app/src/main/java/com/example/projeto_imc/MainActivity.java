package com.example.projeto_imc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private EditText edtPeso, edtAltura;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.edtPeso = findViewById(R.id.editTextText);
        this.edtAltura = findViewById(R.id.editTextText2);
        this.txtResultado = findViewById(R.id.textView2);

        this.edtPeso.addTextChangedListener(new MascaraPesoTextWatcher(edtPeso));
        this.edtAltura.addTextChangedListener(new MascaraAlturaTextWatcher(edtAltura));
    }

    // Método responsável por calcular o IMC ao clicar no Botão
    public void calc(View view){
        if (!edtPeso.getText().toString().equals("") && !edtAltura.getText().toString().equals("")){
            try {
                // Remove apenas espaços, não pontos ou vírgulas
                String pesoString = edtPeso.getText().toString().trim().replace("kg", "").replace(",", ".").trim();
                String alturaString = edtAltura.getText().toString().trim().replace("m", "").replace(",", ".").trim();

                // Converte os valores recebidos para double
                double peso = Double.parseDouble(pesoString);
                double altura = Double.parseDouble(alturaString);

                // Calcula o IMC
                double imc = peso / (altura * altura);

                // Verificação do IMC com base nos dados do usuário
                if (imc < 18.5){
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nBaixo peso");
                } else if (imc >= 18.5 && imc <= 24.9) {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nEutrofia(peso adequado)");
                } else if (imc >= 25 && imc <= 29.9) {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nSobrepeso");
                } else if (imc >= 30 && imc <= 34.9) {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nObesidade grau I");
                } else if (imc >= 35 && imc <= 39.9) {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nObesidade grau II");
                } else {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nObesidade extrema");
                }
            }catch (Exception e){
                Toast.makeText(this, "Erro ao capturar os valores", Toast.LENGTH_SHORT).show();
            }
        }else{
            txtResultado.setText("Resultado");
            Toast.makeText(this, "Preencha todos os campos. ", Toast.LENGTH_SHORT).show();
        }
    }
}