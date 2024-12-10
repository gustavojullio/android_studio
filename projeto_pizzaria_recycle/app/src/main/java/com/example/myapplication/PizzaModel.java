package com.example.myapplication;

import android.widget.Button;

public class PizzaModel {
    String nomePizza;
    String ingredientesPizza;
    double valorPizza;
    Button btnSolicitar;
    int imgPizzaResource;


    // Construtor responsável pela criação de cada componente do RecyclerView.
    public PizzaModel(String nomePizza, String ingredientesPizza, double valorPizza, Button btnSolicitar, int imgPizzaResource){
        this.nomePizza = nomePizza;
        this.ingredientesPizza = ingredientesPizza;
        this.valorPizza = valorPizza;
        this.btnSolicitar = btnSolicitar;
        this.imgPizzaResource = imgPizzaResource;
    }

    public String getNomePizza(){
        return nomePizza;
    }

    public void setNomePizza(String nomePizza) {
        this.nomePizza = nomePizza;
    }

    public String getIngredientesPizza(){
        return ingredientesPizza;
    }

    public void setIngredientesPizza(String ingredientesPizza) {
        this.ingredientesPizza = ingredientesPizza;
    }

    public Double getValorPizza(){
        return valorPizza;
    }

    public void setValorPizza(double valorPizza) {
        this.valorPizza = valorPizza;
    }

    public void setBtnSolicitar(Button btnSolicitar) {
        this.btnSolicitar = btnSolicitar;
    }

    public int getImgPizzaResource(){
        return  imgPizzaResource;
    }

    public void setImgPizzaResource(int imgPizzaResource) {
        this.imgPizzaResource = imgPizzaResource;
    }
    public String toString() {
        return "PizzaModel{" +
                "nomePizza='" + nomePizza + '\'' +
                ", ingredientesPizza='" + ingredientesPizza + '\'' +
                ", valorPizza=" + valorPizza + '\'' +
                '}';
    }
}
