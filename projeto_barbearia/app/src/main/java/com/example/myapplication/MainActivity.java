package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    // Declaração das variáveis
    EditText edtEmail, edtSenha;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicialização das variáveis
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);

        // Inicialização do Firebase
        auth = FirebaseAuth.getInstance();

    }
    // Método responsável por realizar o login do usuário
    public void logar(View view){

        // Recuperação das informações digitadas
        String emailDigitado = edtEmail.getText().toString();
        String senhaDigitada = edtSenha.getText().toString();

        // Verifica se os campos não estão em branco.
        if (!emailDigitado.isEmpty() && !senhaDigitada.isEmpty()){

            auth.signInWithEmailAndPassword(emailDigitado, senhaDigitada).addOnCompleteListener(this, task -> {
                if(task.isSuccessful()){

                    FirebaseUser user = auth.getCurrentUser();

                    // Verifica se o e-mail foi verificado
                    if(user != null && user.isEmailVerified()){

                        Intent intent = new Intent(MainActivity.this, Logado.class);
                        startActivity(intent);
                    }else {
                        // Caso o e-mail não tenha sido verificado
                        Toast.makeText(this, "E-mail não verificado.", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(this)
                                .setTitle("E-mail não verificado")
                                .setMessage("Por favor, verifique seu e-mail antes de logar.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                }else{
                    // Caso o login falhe
                    Toast.makeText(this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                    Log.v("Task Failed Login", task.getException().getMessage());
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