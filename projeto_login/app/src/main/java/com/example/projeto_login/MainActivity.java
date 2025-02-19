package com.example.projeto_login;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario, edtSenha;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);

        auth = FirebaseAuth.getInstance();
    }

    // Método responsável por realizar o login do usuário
    public void logar(View view){

        String usuarioDigitado = edtUsuario.getText().toString();
        String senhaDigitada = edtSenha.getText().toString();

        // Verifica se os campos não estão em branco.
        if (!usuarioDigitado.isEmpty() && !senhaDigitada.isEmpty()){

            auth.signInWithEmailAndPassword(usuarioDigitado, senhaDigitada).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    if(user != null && user.isEmailVerified()){
                        Intent intent = new Intent(MainActivity.this, Logado.class);
                        intent.putExtra("nomeUsuario", usuarioDigitado);
                        startActivity(intent);
                    }else {
                        // Caso o e-mail não tenha sido verificado
                        Toast.makeText(this, "Por favor, verifique seu e-mail antes de logar.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    // Caso o login falhe
                    Toast.makeText(this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                }
            });
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