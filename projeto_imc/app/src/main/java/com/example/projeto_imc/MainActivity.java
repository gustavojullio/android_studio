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
    private Button btn;
    private TextView txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.edtPeso = findViewById(R.id.editTextText);
        this.edtAltura = findViewById(R.id.editTextText2);
        this.btn = findViewById(R.id.button);
        this.txtResultado = findViewById(R.id.textView2);

        //this.edtPeso.addTextChangedListener(new MascaraPesoTextWatcher(edtPeso));
        //this.edtAltura.addTextChangedListener(new MascaraAlturaTextWatcher(edtAltura));



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calc(View view){
        if (!edtPeso.getText().toString().equals("")&& !edtAltura.getText().toString().equals("")){
            try {
                // Remove apenas espaços, não pontos ou vírgulas
                String pesoString = edtPeso.getText().toString().trim().replace("kg", "").replace(",", ".").trim();

                String alturaString = edtAltura.getText().toString().trim().replace("m", "").replace(",", ".").trim();

                double peso = Double.parseDouble(pesoString);
                double altura = Double.parseDouble(alturaString);

                double imc = peso / (altura * altura);



                if (imc < 18.5){
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nSeu IMC é baixo");
                } else if (imc >= 18.5 && imc < 25) {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nSeu IMC está na média correta");
                } else if (imc >= 25 && imc < 30) {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nSeu IMC indica sobrepeso");
                }else {
                    txtResultado.setText("IMC: " + String.format("%.2f", imc) + "\nSeu IMC indica obesidade");
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