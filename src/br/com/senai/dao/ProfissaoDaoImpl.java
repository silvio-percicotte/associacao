/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entidade.Profissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfissaoDaoImpl implements ProfissaoDao {

    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvar(Object object) throws Exception {
        Profissao profissao = (Profissao) object;
        String instrucao = "INSERT INTO profissao(nome, descricao) VALUES(?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao,
                                                          Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, profissao.getNome());
            preparaInstrucao.setString(2, profissao.getDescricao());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            int id = resultado.getInt(1);
            profissao.setId(id);
        } catch (Exception e) {
            System.out.println("erro ao salvar profissão " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
        Profissao profissao = (Profissao) object;
        String instrucao = "UPDATE profissao SET nome = ?, descricao = ? WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, profissao.getNome());
            preparaInstrucao.setString(2, profissao.getDescricao());
            preparaInstrucao.setInt(3, profissao.getId());
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao alterar profissão " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement("DELETE FROM profissao WHERE id = ?");
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao excluir profissão " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public Profissao pesquisarPorId(int id) throws Exception {
        String consulta = "SELECT * FROM profissao WHERE id = ?";
        Profissao profissao = null;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setInt(1, id);
            resultado = preparaInstrucao.executeQuery();
            if (resultado.next()) {
                profissao = new Profissao(resultado.getString("nome"),
                        resultado.getString("descricao"));
                profissao.setId(id);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisarPorID profissão " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return profissao;
    }

    @Override
    public List<Profissao> pesquisarPorNome(String nome) throws Exception {
        String consulta = "SELECT * FROM profissao WHERE nome LIKE ?";
        List<Profissao> profissoes = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, "%" + nome + "%");
            resultado = preparaInstrucao.executeQuery();
            Profissao profissao;
            while(resultado.next()){
                profissao = new Profissao();
                profissao.setId(resultado.getInt("id"));
                profissao.setNome(resultado.getString("nome"));
                profissao.setDescricao(resultado.getString("descricao"));
                profissoes.add(profissao);
            }
        } catch (Exception e) {
            System.out.println("erro ao pesquisarPorNome-profissão " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return profissoes;
    }

}
