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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {

    EditText edtUsuario;
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
        setContentView(R.layout.cadastro);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        edtNome = findViewById(R.id.edtNome);
        edtIdade = findViewById(R.id.edtIdade);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtEndereco = findViewById(R.id.edtEndereco);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
    }

    // Método responsável por criar o cadastro do usuário
    public void cadastrar(View view) {

        // Verifica se as informações foram digitadas para realizar o cadastro

        if (!edtUsuario.getText().toString().isEmpty()) {

            if (!edtSenha.getText().toString().isEmpty()) {

                String nomeUsuario = edtUsuario.getText().toString();
                String senhaUsuario = edtSenha.getText().toString();

                String nome = edtNome.getText().toString();
                String idade = edtIdade.getText().toString();
                String telefone = edtTelefone.getText().toString();
                String endereco = edtEndereco.getText().toString();

                // Verificar se os demais campos não estão vazios
                if ((!nome.isEmpty()) && (!idade.isEmpty()) && (!telefone.isEmpty()) && (!endereco.isEmpty())) {

                    if (senhaUsuario.length() >= 6) {

                       Integer idade2 = Integer.parseInt(idade);
                        auth.createUserWithEmailAndPassword(nomeUsuario, senhaUsuario).addOnCompleteListener(Cadastro.this, task -> {
                            if (task.isSuccessful()) {

                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null) {

                                    user.sendEmailVerification().addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {

                                            String userId = user.getUid();
                                            Usuario novoUsuario = new Usuario(nome, idade2, telefone, endereco, nomeUsuario);
                                            databaseReference.child(userId).setValue(novoUsuario);

                                            Toast.makeText(this, "Por favor, verifique seu E-mail para finalizar o cadastro.", Toast.LENGTH_SHORT).show();

                                            // Redirecionar para a tela de login
                                            Intent intent = new Intent(Cadastro.this, MainActivity.class);
                                            startActivity(intent);
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
                Toast.makeText(this, "Senha do Usuário Obrigatória!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Nome do Usuário Obrigatório!", Toast.LENGTH_SHORT).show();
        }
    }
}