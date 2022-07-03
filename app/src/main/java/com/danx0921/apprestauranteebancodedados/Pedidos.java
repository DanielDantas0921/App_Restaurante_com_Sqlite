package com.danx0921.apprestauranteebancodedados;

public class Pedidos {


    String Nome;
    String Descricao;
    double preco;

    public Pedidos(String nome, String descricao, double preco) {
        Nome = nome;
        Descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return Nome;
    }
}
