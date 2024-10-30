package com.example.projeto_imc;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraPesoTextWatcher implements TextWatcher {

    // Private variable
    private final EditText editText;

    public MascaraPesoTextWatcher(EditText editText){
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!s.equals("")){
            //Interrompe a "escuta" dos caracteres digitados
            editText.removeTextChangedListener(this);

            // Remove todos os caracteres que não são dígitos
            String limpaString = s.toString().replaceAll("[^\\d]", "");

            // Verifica se a string não está vazia após a limpeza
            if (!limpaString.equals("")){
                // Converte os valores recebidos para double
                double valorConvertido = Double.parseDouble(limpaString);
                double valorEmKg = valorConvertido / 100;

                // Formata a String com "kg" na frente do valor
                String formatada = String.format("%.2f kg", valorEmKg);
                editText.setText(formatada);

                // Move o cursor para antes do "kg"
                editText.setSelection(formatada.length() - 3);
            }
            //Retorna a "escutar" os próximos caracteres digitados
            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
