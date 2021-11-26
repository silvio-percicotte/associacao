/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.entidade;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SESI_SENAI
 */
public class Usuario implements Serializable{
   
    private Integer id;
    private String nome;
    private String login;
    private String senha;
    private Date ultimoAcesso;
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(String nome, String login, String senha, Date ultimoAcesso) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.ultimoAcesso = ultimoAcesso;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    
}
