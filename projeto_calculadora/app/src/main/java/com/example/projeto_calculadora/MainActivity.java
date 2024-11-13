package com.example.projeto_calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    TextView txtResultado;
    TextView txtOperacao;

    private String operacao = "";
    private String resultado = "";
    private String operadorProvisorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.txtOperacao = findViewById(R.id.operacao);
        this.txtResultado = findViewById(R.id.resultado);
    }

    /**/
    public void ac(View view) {
        operacao = "";
        resultado = "";
        this.txtResultado.setText("0");
        this.txtOperacao.setText("");
    }

    /**/
    public void numero(View view) {
        Button button = (Button) view;
        operacao += button.getText().toString();
        this.txtOperacao.setText(operacao);
    }

    /**/
    public void ponto(View view) {
        if (!operacao.isEmpty()) {
            operacao += ".";
            this.txtOperacao.setText(operacao);
        } else {
            operacao += "0.";
            this.txtOperacao.setText(operacao);
        }
    }

    /**/
    public void operador(View view) {
        Button button = (Button) view;
        if (!operacao.isEmpty()) {
            // novo
            if (operacao.contains(" ")) {
                // Se a operação já contiver um operador, calcula o resultado
                calcularResultadoParcial();
            }
            operacao += " " + button.getText().toString() + " ";
            this.txtOperacao.setText(operacao);
            operadorProvisorio = button.getText().toString();
        } else {
            Toast.makeText(this, "Insira um numero", Toast.LENGTH_SHORT).show();
        }

    }

    /**/
    public void igual(View view) {
        try {
            resultado = calcularResultado(operacao);
            this.txtResultado.setText(resultado);
            this.txtOperacao.setText(operacao);
            operacao = resultado;

        } catch (Exception e) {
            this.txtResultado.setText("Erro");
        }
    }

    /**/
    public void porcentagem(View view) {
        if (!operacao.isEmpty()) {
            try {
                Double valor = Double.parseDouble(operacao);
                this.txtOperacao.setText(operacao + "%");
                valor = valor / 100;
                this.txtResultado.setText(String.valueOf(valor));
                operacao = String.valueOf(valor);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Insira um numero", Toast.LENGTH_SHORT).show();
        }

    }

    /**/
    public String calcularResultado(String operacao) {
        if (!operacao.isEmpty()) {
            String[] valores = operacao.split(" ");

            double n1 = Double.parseDouble(valores[0]);
            double n2 = Double.parseDouble(valores[2]);
            String operador = valores[1];

            switch (operador) {
                case "+":
                    return String.valueOf(n1 + n2);
                case "-":
                    return String.valueOf(n1 - n2);
                case "x":
                    return String.valueOf(n1 * n2);
                case "/":
                    if (n2 != 0) {
                        return String.valueOf(n1 / n2);
                    } else {
                        return "Não é possível dividir por zero!";
                    }
                default:
                    return "Erro";
            }
        } else {
            return String.valueOf("Vazio");
        }

    }
    private void calcularResultadoParcial() {
        try {
            resultado = calcularResultado(operacao);
            this.txtResultado.setText(resultado);
            operacao = resultado;
        } catch (Exception e) {
            this.txtResultado.setText("Erro");
        }
    }
}