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
import org.junit.Test;
import static org.junit.Assert.*;


public class PerfilDaoImplTest {
    
    private Perfil perfil;
    private PerfilDao perfilDao;
    
    public PerfilDaoImplTest() {
        perfilDao = new PerfilDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        perfil = new Perfil("Gerente Financeiro", "tem perfil de acesso xyz..");
        perfilDao.salvar(perfil);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarPerfilBd();
        perfil.setNome("Perfil alterado");
        perfil.setObservacao("observador alterado");
        perfilDao.alterar(perfil);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarPerfilBd();
        perfilDao.excluir(perfil.getId());
    }
    
    public Perfil buscarPerfilBd() throws Exception{
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement psmt = conn.prepareStatement("SELECT * FROM perfil");
        ResultSet rs = psmt.executeQuery();
        if(rs.next()){
            perfil = new Perfil(rs.getInt("id"), rs.getString("nome"),
                                                              rs.getString("observacao"));
        }else{
            testSalvar();
            buscarPerfilBd();
        }
        return perfil;
    }
    
}
