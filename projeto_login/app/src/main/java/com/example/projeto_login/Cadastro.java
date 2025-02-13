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

public class Cadastro extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastro);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);

        auth = FirebaseAuth.getInstance();


    }
    // Método responsável por criar o cadastro do usuário
    public void cadastrar(View view){

        // Verifica se as informações foram digitadas para realizar o cadastro
        if (!edtUsuario.getText().toString().isEmpty()){

            if (!edtSenha.getText().toString().isEmpty()){

                String nomeUsuario = edtUsuario.getText().toString();
                String senhaUsuario = edtSenha.getText().toString();

                auth.createUserWithEmailAndPassword(nomeUsuario, senhaUsuario).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null){
                            user.sendEmailVerification().addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()){
                                    Toast.makeText(this, "Cadastro realizado. E-mail de confirmação enviado.", Toast.LENGTH_SHORT).show();

                                    // Redirecionar para a tela de login
                                    Intent intent = new Intent(Cadastro.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(this, "Falha ao enviar e-mail de confirmação.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }else{
                        // Caso o cadastro falhe, exibe a mensagem de erro
                        Toast.makeText(this, "Erro no cadastro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(this, "Senha do Usuário Obrigatória!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Nome do Usuário Obrigatório!", Toast.LENGTH_SHORT).show();
        }
    }
}