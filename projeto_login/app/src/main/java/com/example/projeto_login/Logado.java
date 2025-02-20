package com.example.projeto_login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Logado extends AppCompatActivity {

    TextView txtLogado, txtIdade, txtTelefone, txtEndereco;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.logado);

        txtLogado = findViewById(R.id.txtLogado);
        txtIdade = findViewById(R.id.txtIdade);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtEndereco = findViewById(R.id.txtEndereco);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String nome = snapshot.child("nome").getValue(String.class);
                        Integer idade = snapshot.child("idade").getValue(Integer.class); // Correção
                        String telefone = snapshot.child("telefone").getValue(String.class);
                        String endereco = snapshot.child("endereco").getValue(String.class); // Correção do nome do campo

                        txtLogado.setText("Seja bem-vindo " + nome + "!");
                        txtIdade.setText("Idade: " + (idade != null ? idade : "Não informado"));
                        txtTelefone.setText("Telefone: " + telefone);
                        txtEndereco.setText("Endereço: " + endereco);
                    } else {
                        txtLogado.setText("Usuário não encontrado no banco de dados.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    txtLogado.setText("Erro ao recuperar informações do usuário.");
                }
            });
        } else {
            txtLogado.setText("Erro: Usuário não está autenticado.");
        }
    }
}
