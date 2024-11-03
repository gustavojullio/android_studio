package com.example.projeto_delivery;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtQtd;
    private TextView txtValorTotal;
    private TextView txtMsgFinal;
    private static int qtd = 0;
    private static final double PRECO_UNITARIO = 25.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.edtNome = findViewById(R.id.edtNome);
        this.edtQtd = findViewById(R.id.edtQtd);
        this.txtValorTotal = findViewById(R.id.txtValorTotal);
        this.txtMsgFinal = findViewById(R.id.txtMsgFinal);

        this.edtQtd.setText(qtd + "");
        this.txtValorTotal.setText("Valor Total: R$ 0,00");


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void remover(View view){
        if (qtd == 0){
            Toast.makeText(this, "Não é possível remover mais", Toast.LENGTH_SHORT).show();
        }else{
            qtd--;
            this.edtQtd.setText(qtd + "");
            atualizaValorTotal();

        }
    }

    public void adicionar(View view){
        if (qtd >= 10){
            Toast.makeText(this, "Não é possível adicionar mais de 10 unidades.", Toast.LENGTH_SHORT).show();
        }else{
            qtd++;
            this.edtQtd.setText(qtd + "");
            atualizaValorTotal();
        }
    }

    public void atualizaValorTotal(){
        double total = PRECO_UNITARIO * qtd;
        this.txtValorTotal.setText(String.format("Valor Total: R$ %.2f", total));
    }

    public void finalizar(View view){
        if (!edtNome.getText().toString().equals("")){
            if (qtd > 0){
                String nome= edtNome.getText().toString();
                this.txtMsgFinal.setText(String.format("Obrigado %s por comprar conosco! Você pediu %d item(ns).", nome, qtd));
            }else{
                Toast.makeText(this, "Por favor, adicione ao menos 1 item ao pedido.", Toast.LENGTH_SHORT).show();
                this.txtValorTotal.setText("Valor Total: R$ 0,00");
                this.txtMsgFinal.setText("");
            }



        }else{
            Toast.makeText(this, "É obrigatório se identificar.", Toast.LENGTH_SHORT).show();
            qtd = 0;
            this.edtQtd.setText(qtd + "");
            this.txtValorTotal.setText("Valor Total: R$ 0,00");
            this.txtMsgFinal.setText("");

        }


    }





}