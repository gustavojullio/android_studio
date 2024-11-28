package com.example.projeto_calculadora;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //TextViews responsáveis por exibir a operação e o resultado na tela
    TextView txtResultado;
    TextView txtOperacao;

    //Strings responsáveis por armazenar a operação que está sendo construída e o resultado.
    private String operacao = "";
    private String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        this.txtOperacao = findViewById(R.id.operacao);
        this.txtResultado = findViewById(R.id.resultado);
    }

    // Método para resetar a operação e o resultado, limpando os TextViews
    public void ac(View view) {
        operacao = "";
        resultado = "";
        this.txtResultado.setText("0");
        this.txtOperacao.setText("");
    }

    // Método responsável por capturar o número digitado pelo usuário ao clicar no botão
    public void numero(View view) {
        // Obtém o texto do botão precionado que é o número correspondente e adiciona na operação.
        Button button = (Button) view;

        // Verifica se o último cálculo foi exibido e não há operador na operação
        if (!resultado.isEmpty() && !operacao.contains(" ")) {
            // Reinicia a operação ao digitar um novo número, iniciando uma nova expressão
            operacao = "";
        }

        operacao += button.getText().toString();
        this.txtOperacao.setText(operacao);

        // Limpa o resultado (para evitar conflitos em novas operações)
        resultado = "";
    }




    // Método responsável por adicionar ponto decimal no numero.
    public void ponto(View view) {
        // Se já houver um numero, acrescenta-se o ponto nesse número
        if (!operacao.isEmpty()) {
            // Divide a operação em partes para verificar o número atual
            String[] partes = operacao.split(" ");

            // Caso não tenha operador, verifica o número antes de adicionar o ponto
            if (!operacao.contains(" ")) {
                if (!operacao.contains(".")) {
                    operacao += ".";
                    this.txtOperacao.setText(operacao);
                } else {
                    Toast.makeText(this, "Número já contém ponto decimal", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Se há operador, verifica o último número
                String ultimoNumero = partes[partes.length - 1];
                if (!ultimoNumero.contains(".")) {
                    operacao += ".";
                    this.txtOperacao.setText(operacao);
                } else {
                    Toast.makeText(this, "Número já contém ponto decimal", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // Caso não haja número, inicia com "0."
            operacao += "0.";
            this.txtOperacao.setText(operacao);
        }
    }

    // Método responável por adicionar um operador a operação(+,-,x,/)
    public void operador(View view) {
        Button button = (Button) view;
        if (!operacao.isEmpty()) {
            // Se já houver um operador na operação, realiza o cálculo parcial antes de adicionar um novo operador
            if (operacao.contains(" ")) {
                // Divide a operação em partes
                String[] partes = operacao.split(" ");
                if (partes.length == 2) {
                    // Substitui o operador atual pelo novo
                    operacao = partes[0] + " " + button.getText().toString() + " ";
                } else if (partes.length == 3) {
                    // Calcula parcialmente
                    calcularResultadoParcial();
                    operacao += " " + button.getText().toString() + " ";
                }
            }else{
                // Caso ainda não tenha operador, adiciona o operador normalmente
                operacao += " " + button.getText().toString() + " ";
            }
            this.txtOperacao.setText(operacao);
        } else {
            // Aviso se o usuário tentar usar um operador sem um número
            Toast.makeText(this, "Insira um numero", Toast.LENGTH_SHORT).show();
        }

    }

    // Método responsável por iniciar os cálculos quando o botão "=" é precionado
    public void igual(View view) {
        // Tenta calcular o resultado da operação, chamando o método responsável por "quebrar" a String operação
        try {
            resultado = calcularResultado(operacao);
            this.txtResultado.setText(resultado);
            this.txtOperacao.setText(operacao);
            // Armazena o resultado na variável de operação para permitir mais operações com o resultado
            operacao = resultado;
        } catch (Exception e) {
            // Exibe "Erro" se algo der errado durante o cálculo
            this.txtResultado.setText("Erro");
        }
    }

    // Função responsável por realizar o cálculo com base na operação fornecida
    public String calcularResultado(String operacao) {
        if (!operacao.isEmpty()) {
            // Divide a operação em três partes n1, operador e n2
            String[] valores = operacao.split(" ");

            double n1 = Double.parseDouble(valores[0]);
            double n2 = Double.parseDouble(valores[2]);
            String operador = valores[1];

            // Realiza a operação de acordo com o operador
            switch (operador) {
                case "+":
                    return String.valueOf(n1 + n2);
                case "-":
                    return String.valueOf(n1 - n2);
                case "x":
                    return String.valueOf(n1 * n2);
                case "/":
                    if (n2 != 0) {
                        return String.valueOf(n1 / n2);
                    } else {
                        return "Não é possível dividir por zero!";
                    }
                default:
                    return "Erro";
            }
        } else {
            return String.valueOf("Vazio");
        }
    }

    // Método responsável por calcular a porcentagem do número digitado.
    public void porcentagem(View view) {
        if (!operacao.isEmpty()) {
            try {
                // Converte o valor atual em um número e divide por 100
                Double valor = Double.parseDouble(operacao);
                this.txtOperacao.setText(operacao + "%");
                valor = valor / 100;
                this.txtResultado.setText(String.valueOf(valor));
                operacao = String.valueOf(valor);
            } catch (Exception e) {
                // Exibe uma mensagem se ocorrer um erro
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            // Exibe uma mensagem se o usuário tentar calcular porcentagem sem valor
            Toast.makeText(this, "Insira um número para calcular porcentagem", Toast.LENGTH_SHORT).show();
        }
    }

    // Calcula o resultado parcial quando há um operador presente
    private void calcularResultadoParcial() {
        try {
            resultado = calcularResultado(operacao);
            this.txtResultado.setText(resultado);
            operacao = resultado;
        } catch (Exception e) {
            this.txtResultado.setText("Erro");
        }
    }

    // Método responsável por inverter o sinal do número
    public void inverterSinal(View view) {
        if (!operacao.isEmpty()) {
            try {
                // Verifica se a operação atual contém um operador
                if (!operacao.contains(" ")) {
                    // Inverte o sinal do número atual
                    double valor = Double.parseDouble(operacao);
                    valor = valor * -1;
                    operacao = String.valueOf(valor);
                    this.txtOperacao.setText(operacao);
                } else {
                    // Se já houver uma operação com operador, inverte o sinal do segundo número
                    String[] valores = operacao.split(" ");
                    if (valores.length == 3) {
                        double valor = Double.parseDouble(valores[2]);
                        valor = valor * -1;
                        valores[2] = String.valueOf(valor);
                        operacao = valores[0] + " " + valores[1] + " " + valores[2];
                        this.txtOperacao.setText(operacao);
                    }
                }
            } catch (NumberFormatException e) {
                // Exibe um aviso se houver erro ao tentar inverter o sinal
                Toast.makeText(this, "Erro ao inverter sinal", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Exibe um aviso se o usuário tentar inverter o sinal sem número
            Toast.makeText(this, "Insira um número para inverter o sinal", Toast.LENGTH_SHORT).show();
        }
    }
}