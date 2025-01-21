package com.example.proejto_to_do_list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtTarefa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtTarefa = findViewById(R.id.edtTarefa);

        if (edtTarefa.getText().equals("")){

        }



    }
    public void adicionar(View view) {





    }

    public void editar(View view) {

        new AlertDialog.Builder(this)
                .setTitle("Digitar Tarefa")
                .setMessage("TESTE")
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // fecha o dialog
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // fecha o dialog ao pressionar "Cancelar"
                    }
                })
                .setCancelable(false) // impede o fechamento do diálogo ao tocar fora dele
                .show();
    }
}