/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entidade.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Statement;

public class PerfilDaoImpl implements PerfilDao {

    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvar(Object object) throws Exception {
        Perfil perfil = (Perfil) object;
        String instrucao = "INSERT INTO perfil(nome, observacao) VALUES(?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao,
                    Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, perfil.getNome());
            preparaInstrucao.setString(2, perfil.getObservacao());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            perfil.setId(resultado.getInt(1));
        } catch (Exception e) {
            System.out.println("erro ao salvar perfil " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
        Perfil perfil = (Perfil) object;
        String instrucao = "UPDATE perfil SET nome = ?, observacao = ? WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, perfil.getNome());
            preparaInstrucao.setString(2, perfil.getObservacao());
            preparaInstrucao.setInt(3, perfil.getId());
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.err.println("erro ao alterar perfil " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement("DELETE FROM perfil WHERE id = ?");
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.err.println("erro ao excluir perfil " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public List<Perfil> pesquisarTodo() throws Exception {
        String instrucao = "SELECT * FROM perfil";
        List<Perfil> perfis = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            resultado = preparaInstrucao.executeQuery();
            Perfil perfil;
            while (resultado.next()) {                
                perfil = new Perfil();
                perfil.setId(resultado.getInt("id"));
                perfil.setNome(resultado.getString("nome"));
                perfis.add(perfil);
            }
        } catch (Exception e) {
            System.err.println("erro ao pesquisar todos os  perfil " +
                                                             e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return perfis;
    }

}
