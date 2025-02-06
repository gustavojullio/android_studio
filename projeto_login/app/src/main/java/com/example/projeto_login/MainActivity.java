package com.example.projeto_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText edtUsuario, edtSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
    }

    // Método responsável por realizar o login do usuário
    public void logar(View view){
        // Verifica se os campos não estão em branco.
        if (!edtUsuario.getText().toString().isEmpty() && !edtSenha.getText().toString().isEmpty()){
            String usuarioDigitado = edtUsuario.getText().toString();
            String senhaDigitada = edtSenha.getText().toString();

            // Recupera as informações salvas no SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("dadosUsuario", MODE_PRIVATE);
            String usuarioCadastrado = sharedPreferences.getString("nomeUsuario", "");
            String senhaCadastrada = sharedPreferences.getString("senhaUsuario", "");

            // Verifica se o usuário possui uma conta cadastrada.
            if (usuarioDigitado.equals(usuarioCadastrado) && senhaDigitada.equals(senhaCadastrada)){
                Intent intent = new Intent(MainActivity.this, Logado.class);
                intent.putExtra("nomeUsuario", usuarioCadastrado);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Usuário não cadastrado!!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Dados faltando. Por favor, revise as informações!", Toast.LENGTH_SHORT).show();
        }

    }
    // Método responsável por direcionar o usuário a uma view de cadastro
    public void cadastrar(View view){
        Intent intent = new Intent(MainActivity.this, Cadastro.class);
        startActivity(intent);
    }
}