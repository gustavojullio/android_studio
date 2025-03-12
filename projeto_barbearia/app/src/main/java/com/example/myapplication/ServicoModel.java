package com.example.myapplication;

public class ServicoModel {
    // Declaração das variáveis
    String nomeServico;
    int imgServicoResource;

    // Construtor responsável pela criação de cada componente do RecyclerView.
    public ServicoModel(String nomeServico, int imgServicoResourceResource){
        this.nomeServico = nomeServico;
        this.imgServicoResource = imgServicoResourceResource;
    }

    // Métodos Getters e Setters
    public String getNomeServico(){
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public int getImgServicoResource(){
        return  imgServicoResource;
    }

}
