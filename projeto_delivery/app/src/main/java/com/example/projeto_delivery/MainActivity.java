package com.example.projeto_delivery;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private EditText edtNome;
    private EditText edtQtd;
    private TextView txtValorTotal;
    private TextView txtMsgFinal;
    private Button btnAdicionar;
    private Button btnRemover;
    private static int qtd = 0;
    private static final double PRECO_UNITARIO = 25.00;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Captura os id's da tela após a criação da tela
        this.edtNome = findViewById(R.id.edtNome);
        this.edtQtd = findViewById(R.id.edtQtd);
        this.txtValorTotal = findViewById(R.id.txtValorTotal);
        this.txtMsgFinal = findViewById(R.id.txtMsgFinal);
        this.btnAdicionar = findViewById(R.id.btnAdicionar);
        this.btnRemover = findViewById(R.id.btnRemover);

        // Inicializa com a quantidade zero na tela.
        this.edtQtd.setText(qtd + "");
        this.txtValorTotal.setText("");
    }

    /* Remove em uma unidade a quantidade do item */
    @SuppressLint("SetTextI18n")
    public void remover(View view){
        if (qtd == 0){
            Toast.makeText(this, "Não é possível remover mais", Toast.LENGTH_SHORT).show();
        }else{
            qtd--;
            this.edtQtd.setText(qtd + "");
            atualizaValorTotal();

        }
    }

    /* Adiciona em uma unidade a quantidade do item*/
    @SuppressLint("SetTextI18n")
    public void adicionar(View view){
        if (qtd >= 10){
            Toast.makeText(this, "Não é possível adicionar mais de 10 unidades.", Toast.LENGTH_SHORT).show();
        }else{
            qtd++;
            this.edtQtd.setText(qtd + "");
            atualizaValorTotal();
        }
    }

    /*Calcula o novo valor e atualiza na tela*/
    @SuppressLint("DefaultLocale")
    public void atualizaValorTotal(){
        double total = PRECO_UNITARIO * qtd;
        if(total > 0){
            this.txtValorTotal.setText(String.format("Valor Total: R$ %.2f", total));
        }else{
            this.txtValorTotal.setText("");
        }

    }

    /*Realiza as verificações necessárias para finalizar o pedido e se estiver tudo correto exibe as informações na tela*/
    @SuppressLint("DefaultLocale")
    public void finalizar(View view){
        if (!edtNome.getText().toString().isEmpty()){
            if (qtd > 0){
                String nome= edtNome.getText().toString();
                this.txtMsgFinal.setText(String.format("Obrigado %s por comprar conosco! Você pediu %d item(ns).", nome, qtd));
                this.btnRemover.setEnabled(false);
                this.btnAdicionar.setEnabled(false);
                this.edtNome.setEnabled(false);

            }else{
                Toast.makeText(this, "Por favor, adicione ao menos 1 item ao pedido.", Toast.LENGTH_SHORT).show();
                this.txtValorTotal.setText("");
                this.txtMsgFinal.setText("");
            }
        }else{
            Toast.makeText(this, "É obrigatório se identificar.", Toast.LENGTH_SHORT).show();
            this.txtMsgFinal.setText("");
        }
    }
}