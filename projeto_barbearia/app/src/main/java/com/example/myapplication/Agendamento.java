package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Agendamento extends AppCompatActivity {
    TextView txtAgendamento;
    RadioGroup radioGroupProfissional;
    CalendarView calendarView;
    TimePicker timePicker;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendamento);

        // Inicializa os componentes da interface
        txtAgendamento = findViewById(R.id.txtAgendamento);
        radioGroupProfissional = findViewById(R.id.radioGroupProfissional);
        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.timePicker);

        // Inicializa o Firebase
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Clientes");

        // Listener para a seleção de data no CalendarView
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            Toast.makeText(Agendamento.this, "Data selecionada: " + selectedDate, Toast.LENGTH_SHORT).show();
        });

        // Configura o TimePicker para permitir apenas horários entre 08:00 e 18:00
        timePicker.setHour(8);
        timePicker.setMinute(0);
        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            if (hourOfDay < 8) {
                timePicker.setHour(8);
            } else if (hourOfDay > 18) {
                timePicker.setHour(18);
            }
        });

        // Listener para a seleção do profissional no RadioGroup
        radioGroupProfissional.setOnCheckedChangeListener((group, checkedId) -> {
            String selecionado = "";
            if (checkedId == R.id.radioDaniel) {
                selecionado = "Daniel";
            } else if (checkedId == R.id.radioPedro) {
                selecionado = "Pedro";
            } else if (checkedId == R.id.radioAndre) {
                selecionado = "Andre";
            }
            Toast.makeText(Agendamento.this, "Profissional selecionado: " + selecionado, Toast.LENGTH_SHORT).show();
        });
    }

    // Método para agendar o serviço
    public void agendar(View view) {
        int marcado = radioGroupProfissional.getCheckedRadioButtonId();
        if (marcado == -1) {
            Toast.makeText(this, "Selecione um profissional antes de prosseguir!", Toast.LENGTH_SHORT).show();
        } else {
            // Recupera os dados do agendamento
            String nomeServico = getIntent().getStringExtra("nomeServico"); // Nome do serviço
            long dataSelecionada = calendarView.getDate(); // Data selecionada em milissegundos
            int horaSelecionada = timePicker.getHour(); // Hora selecionada
            int minutoSelecionado = timePicker.getMinute(); // Minuto selecionado

            // Recupera o profissional selecionado
            String profissional = "";
            if (marcado == R.id.radioDaniel) {
                profissional = "Daniel";
            } else if (marcado == R.id.radioPedro) {
                profissional = "Pedro";
            } else if (marcado == R.id.radioAndre) {
                profissional = "Andre";
            }

            // Verifica se o usuário está autenticado
            if (auth.getCurrentUser() == null) {
                Toast.makeText(this, "Usuário não autenticado. Faça login novamente.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Recupera o ID do usuário autenticado
            String userId = auth.getCurrentUser().getUid();

            // Cria um objeto AgendamentoModel com os dados
            AgendamentoModel agendamento = new AgendamentoModel(nomeServico, dataSelecionada, horaSelecionada, minutoSelecionado, profissional);

            // Salva o agendamento no Firebase
            databaseReference.child(userId).child("agendamentos").push().setValue(agendamento)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Agendamento.this, "Agendamento realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            // Redireciona para a tela principal (opcional)
                            // startActivity(new Intent(Agendamento.this, MainActivity.class));
                        } else {
                            Toast.makeText(Agendamento.this, "Erro ao realizar agendamento.", Toast.LENGTH_SHORT).show();
                            if (task.getException() != null) {
                                Log.e("Agendamento", "Erro: " + task.getException().getMessage());
                            }
                        }
                    });
        }
    }
}