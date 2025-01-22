package com.example.proejto_to_do_list;

public class TarefaModel {
    String nomeTarefa;
    boolean realizada;

    // Construtor responsável pela criação de cada componente do RecyclerView.
    public TarefaModel(String nomeTarefa, boolean realizada){
        this.nomeTarefa = nomeTarefa;
        this.realizada = realizada;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }
}
