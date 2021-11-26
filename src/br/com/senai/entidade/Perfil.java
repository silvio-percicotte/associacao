/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entidade;

import java.util.List;


public class Perfil {
    private Integer id;
    private String nome;
    private String observacao;

    public Perfil() {
    }

    public Perfil(String nome, String observacao) {
        this.nome = nome;
        this.observacao = observacao;
    }
    
    public Perfil(Integer id, String nome, String observacao) {
        this.id = id;
        this.nome = nome;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
