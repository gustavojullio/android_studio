package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Agendamento extends AppCompatActivity {

    // Declaração das variáveis e banco de dados
    TextView txtAgendamento;
    RadioGroup radioGroupProfissional;
    CalendarView calendarView;
    TimePicker timePicker;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String profissional = "";
    String selectedDate = "";

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
            // Formata e exibe a data selecionada
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });

        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            // Verifica se a hora é menor que 8 ou maior que 18
            if (hourOfDay < 8) {
                // Define a hora mínima como 08:00
                timePicker.setHour(8);
                // Define os minutos como 00
                timePicker.setMinute(0);
            } else if (hourOfDay > 18) {
                // Define a hora máxima como 18:00
                timePicker.setHour(18);
                // Define os minutos como 00
                timePicker.setMinute(0);
            } else if (hourOfDay == 18 && minute > 0) {
                // Se a hora for 18 e os minutos forem maiores que 0, define os minutos como 00
                timePicker.setMinute(0);
            }
        });

        // Listener para a seleção do profissional no RadioGroup
        radioGroupProfissional.setOnCheckedChangeListener((group, checkedId) -> {
            String selecionado = "";
            if (checkedId == R.id.radioDaniel) {
                selecionado = "Daniel Carvalho";
            } else if (checkedId == R.id.radioPedro) {
                selecionado = "Pedro Silva";
            } else if (checkedId == R.id.radioAndre) {
                selecionado = "André Rodrigues";
            }
        });
    }

    // Método para agendar o serviço
    public void agendar(View view) {
        // Verifica se algum profissional foi selecionado
        int marcado = radioGroupProfissional.getCheckedRadioButtonId();

        if (marcado == -1) {
            Toast.makeText(this, "Selecione um profissional antes de prosseguir!", Toast.LENGTH_SHORT).show();
        } else {
            // Recupera os dados do agendamento
            String nomeServico = getIntent().getStringExtra("nomeServico"); // Nome do serviço
            //long dataSelecionada = calendarView.getDate(); // Data selecionada em milissegundos
            int horaSelecionada = timePicker.getHour(); // Hora selecionada
            int minutoSelecionado = timePicker.getMinute(); // Minuto selecionado

            // Verifica e atribui o nome do profissional com base na seleção
            if (marcado == R.id.radioDaniel) {
                profissional = "Daniel Carvalho";
            } else if (marcado == R.id.radioPedro) {
                profissional = "Pedro Silva";
            } else if (marcado == R.id.radioAndre) {
                profissional = "André Rodrigues";
            }

            // Verifica se o usuário está autenticado
            if (auth.getCurrentUser() == null) {
                Toast.makeText(this, "Usuário não autenticado. Faça login novamente.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Recupera o ID do usuário autenticado
            String userId = auth.getCurrentUser().getUid();

            if(selectedDate.equals("")){
                long dataSelecionada = calendarView.getDate(); // Data selecionada em milissegundos
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy");
                selectedDate = sdf.format(new java.util.Date(dataSelecionada));
            }

            // Cria um objeto AgendamentoModel com os dados
            AgendamentoModel agendamento = new AgendamentoModel(nomeServico, selectedDate, horaSelecionada, minutoSelecionado, profissional);

            // Salva o agendamento no Firebase
            databaseReference.child(userId).child("agendamentos").push().setValue(agendamento)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            showAgendamentoDialog(nomeServico, profissional, selectedDate, horaSelecionada, minutoSelecionado);
                        } else {
                            Toast.makeText(Agendamento.this, "Erro ao realizar agendamento.", Toast.LENGTH_SHORT).show();
                            if (task.getException() != null) {
                                Log.e("Agendamento", "Erro: " + task.getException().getMessage());
                            }
                        }
                    });
        }
    }
    private void showAgendamentoDialog(String nomeServico, String profissional, String data, int hora, int minuto) {
        // Formata a data para exibir no formato dia, mes e ano
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        //String dataFormatada = sdf.format(new java.util.Date(data));

        // Formata a hora para exibir no formato 24h
        String horaFormatada = String.format("%02d:%02d", hora, minuto);

        // Exibe o AlertDialog com o resumo do agendamento
        new AlertDialog.Builder(this)
                .setTitle("Agendamento realizado com sucesso!")
                .setMessage("Resumo do Agendamento:\n\n" +
                        "Profissional: " + profissional + "\n" +
                        "Serviço: " + nomeServico + "\n" +
                        "Data: " + selectedDate + "\n" +
                        "Horário: " + horaFormatada)
                .setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Agendamento.this, MainActivity.class));
                        finish();
                    }
                })
                .setNeutralButton("Adicionar Novo Serviço", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Redireciona para a tela Logado para adicionar novo serviço
                        startActivity(new Intent(Agendamento.this, Logado.class));
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }
}