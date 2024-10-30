package com.example.projeto_alcool_gasolina;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyTextWatcher implements TextWatcher {

    private final EditText editText;

    public MoneyTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!charSequence.equals("")) {

            // Para de escutar para tratar o valor que foi digitado pelo usuário
            editText.removeTextChangedListener(this);

            // Remove todos os caracteres que não são dígitos
            String clearString = charSequence.toString().replaceAll("[^\\d]", "");

            // Converte o valor recebido para double
            double gasolinaConvertida = Double.parseDouble(clearString);

            // Formata o valor para reais adicionado o "R$"
            String formatted = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(gasolinaConvertida / 100);

            // Define o texto no EditText
            editText.setText(formatted);

            // Move o cursor para o final
            editText.setSelection(formatted.length());

            // Readiciona o TextChangedListener
            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
