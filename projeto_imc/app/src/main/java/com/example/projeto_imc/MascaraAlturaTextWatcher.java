package com.example.projeto_imc;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MascaraAlturaTextWatcher implements TextWatcher{

    // Private variables
    private final EditText editText;

    public MascaraAlturaTextWatcher(EditText editText){
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!s.equals("")){
            // Interrompe a "escuta" dos caracteres digitados
            editText.removeTextChangedListener(this);

            // Remove todos os caracteres que não são dígitos
            String limpaString = s.toString().replaceAll("[^\\d]", "");

            // Verifica se a string não está vazia após a limpeza
            if (!limpaString.equals("")){
                // Converte os valores recebidos para double
                double valorConvertido = Double.parseDouble(limpaString);
                double valorEmMt = valorConvertido / 100;

                // Formata a String com "m" na frente do valor
                String formatada = String.format("%.2f m", valorEmMt);

                editText.setText(formatada);

                // Move o cursor para antes do " m"
                editText.setSelection(formatada.length() - 2);
            }
            //Retorna a "escutar" os próximos caracteres digitados
            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
