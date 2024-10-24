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

            //
            editText.removeTextChangedListener(this);

            //
            String clearString = charSequence.toString().replaceAll("[^\\d]", "");
            //
            double gasolinaConvertida = Double.parseDouble(clearString);

            //
            String formatted = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(gasolinaConvertida / 100);

            //
            editText.setText(formatted);

            //
            editText.setSelection(formatted.length());

            editText.addTextChangedListener(this);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
