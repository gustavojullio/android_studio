package com.example.myapplication;

public class ServicoModel {
    String nomeServico;
    int imgServicoResource;


    // Construtor responsável pela criação de cada componente do RecyclerView.
    public ServicoModel(String nomeServico, int imgServicoResourceResource){
        this.nomeServico = nomeServico;
        this.imgServicoResource = imgServicoResourceResource;
    }

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
