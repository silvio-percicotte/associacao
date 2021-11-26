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
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfissaoDaoImplTest {

    private Profissao profissao;
    private ProfissaoDao profissaoDao;

    public ProfissaoDaoImplTest() {
        profissaoDao = new ProfissaoDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        profissao = new Profissao("Programador POO", "usa Herança e polimorfismo");
        profissaoDao.salvar(profissao);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarProfissaoBD();
        profissao.setNome("nome alterado");
        profissaoDao.alterar(profissao);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarProfissaoBD();
        System.out.println("ID " + profissao.getId());
        profissaoDao.excluir(profissao.getId());
    }

//    @Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("Pesquisar por ID");
        buscarProfissaoBD();
        Profissao profissaoNovo = profissaoDao.pesquisarPorId(profissao.getId());
        mostrarProfissao(profissaoNovo);
    }

//    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("Pesquisar por Nome");
        buscarProfissaoBD();
        List<Profissao> profissoes = profissaoDao.pesquisarPorNome(profissao.getNome());
        for (Profissao profissao : profissoes) {
            mostrarProfissao(profissao);
        }
    }

    private void mostrarProfissao(Profissao prof) {
        System.out.println("Id: " + prof.getId());
        System.out.println("Nome: " + prof.getNome());
        System.out.println("Descrição: " + prof.getDescricao());
        System.out.println("");
    }

    public Profissao buscarProfissaoBD() throws Exception {
        String consulta = "SELECT * FROM profissao";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        if (resultado.next()) {
            profissao = new Profissao();
            profissao.setNome(resultado.getString("nome"));
            profissao.setDescricao(resultado.getString("descricao"));
            profissao.setId(resultado.getInt("id"));
        } else {
            testSalvar();
            buscarProfissaoBD();
        }
        return profissao;
    }

}
