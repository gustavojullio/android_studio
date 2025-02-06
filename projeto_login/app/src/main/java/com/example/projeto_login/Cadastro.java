package com.example.projeto_login;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity {
    EditText edtUsuario;
    EditText edtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastro);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);

    }
    // Método responsável por criar o cadastro do usuário
    public void cadastrar(View view){
        // Verifica se as informações foram digitadas para realizar o cadastro
        if (!edtUsuario.getText().toString().isEmpty()){
            if (!edtSenha.getText().toString().isEmpty()){
                String nomeUsuario = edtUsuario.getText().toString();
                String senhaUsuario = edtSenha.getText().toString();

                // Salva as informações no SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("dadosUsuario", MODE_PRIVATE);
                SharedPreferences.Editor editor =  sharedPreferences.edit();

                editor.putString("nomeUsuario", nomeUsuario);
                editor.putString("senhaUsuario", senhaUsuario);

                editor.apply();

                // Retorna a view de login
                Intent intent = new Intent(Cadastro.this, MainActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Senha do Usuário Obrigatória!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Nome do Usuário Obrigatório!", Toast.LENGTH_SHORT).show();
        }
    }
}