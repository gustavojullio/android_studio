package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {
    // Declaração das variáveis para as entradas
    EditText edtEmail;
    EditText edtSenha;
    EditText edtNome;
    EditText edtIdade;
    EditText edtTelefone;
    EditText edtEndereco;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_cadastro);
        // Inicialização das variáveis
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEndereco = findViewById(R.id.edtEndereco);

        // Inicialização do Firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Clientes");
    }

    // Método responsável por criar o cadastro do usuário
    public void cadastrar(View view) {

        // Recuperação das informações digitadas
        String email = edtEmail.getText().toString();
        String senhaUsuario = edtSenha.getText().toString();
        String nome = edtNome.getText().toString();
        String idade = edtIdade.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String endereco = edtEndereco.getText().toString();


        // Verifica se as informações foram digitadas para realizar o cadastro
        if (!email.isEmpty()) {

            if (!senhaUsuario.isEmpty()) {

                // Verificar se os demais campos não estão vazios
                if ((!nome.isEmpty()) && (!idade.isEmpty()) && (!telefone.isEmpty()) && (!endereco.isEmpty())) {

                    if (senhaUsuario.length() >= 6) {

                        Integer idade2 = Integer.parseInt(idade);
                        auth.createUserWithEmailAndPassword(email, senhaUsuario).addOnCompleteListener(Cadastro.this, task -> {
                            if (task.isSuccessful()) {

                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {

                                    user.sendEmailVerification().addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            // Recupera o ID do usuário
                                            String userId = user.getUid();
                                            // Cria um objeto Usuario com os dados
                                            Usuario novoUsuario = new Usuario(nome, idade2, telefone, endereco, email);

                                            // Salva o usuário no Firebase
                                            databaseReference.child(userId).setValue(novoUsuario);

                                            // Exibe uma mensagem informando que o e-mail de verificação foi enviado
                                            new AlertDialog.Builder(Cadastro.this)
                                                    .setTitle("E-mail enviado")
                                                    .setMessage("Por favor, verifique seu E-mail para finalizar o cadastro.")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            // Redirecionar para a tela de login
                                                            Intent intent = new Intent(Cadastro.this, MainActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    })
                                                    .setCancelable(false)
                                                    .show();
                                        } else {
                                            Toast.makeText(this, "Falha ao enviar e-mail de confirmação.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                // Caso o cadastro falhe, exibe a mensagem de erro
                                Toast.makeText(this, "Erro no cadastro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(this, "Senha precisa ter ao menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "É necessário digitar uma senha!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "É necessário digitar um E-mail!", Toast.LENGTH_SHORT).show();
        }
    }
}