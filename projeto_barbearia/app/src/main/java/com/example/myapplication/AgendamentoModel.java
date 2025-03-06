package com.example.myapplication;

public class AgendamentoModel {
    private String nomeServico;
    private long data;
    private int hora;
    private int minuto;
    private String profissional;

    public AgendamentoModel() {
        // Construtor vazio necessário para o Firebase
    }

    public AgendamentoModel(String nomeServico, long data, int hora, int minuto, String profissional) {
        this.nomeServico = nomeServico;
        this.data = data;
        this.hora = hora;
        this.minuto = minuto;
        this.profissional = profissional;
    }

    // Getters e Setters
    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public String getProfissional() {
        return profissional;
    }

    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }
}