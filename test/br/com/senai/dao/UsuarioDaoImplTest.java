/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entidade.Perfil;
import br.com.senai.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SESI_SENAI
 */
public class UsuarioDaoImplTest {

    private Usuario usuario;
    private UsuarioDao usuarioDao;

    public UsuarioDaoImplTest() {
        usuarioDao = new UsuarioDaoImpl();
    }

    @Test
    public void testSalvar() throws Exception {
        PerfilDaoImplTest perfilDaoTest = new PerfilDaoImplTest();
        System.out.println("salvar");
        usuario = new Usuario("Junior Siclano", "fulano@", "12345", null);
        // metodo da classe PerfilDaoImplTest que retorna um perfil do BD
        Perfil perfil = perfilDaoTest.buscarPerfilBd();
        usuario.setPerfil(perfil);
        usuarioDao.salvar(usuario);
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarUsuarioBD();
        usuario.setNome("usuário alterado");
        usuario.setUltimoAcesso(new Date());
        usuarioDao.alterar(usuario);
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarUsuarioBD();
        usuarioDao.excluir(usuario.getId());
    }

//    @Test
    public void testLogarSucesso() throws Exception {
        System.out.println("logar sucesso");
        buscarUsuarioBD();
        Usuario usuarioLogado = usuarioDao.logar(usuario.getLogin(), usuario.getSenha());
        assertNotNull(usuarioLogado);
    }
    
//    @Test
    public void testLogarFalso() throws Exception {
        System.out.println("logar com erro");
        Usuario usuarioLogado = usuarioDao.logar("", "");
        assertNull(usuarioLogado);
    }

    public Usuario buscarUsuarioBD() throws Exception {
        String consulta = "SELECT u.id u_id, u.nome u_nome, pe.nome p_nome,"
                + " u.*, pe.* from usuario u "
                + " join perfil pe on u.idperfil = pe.id ";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement pstm = conn.prepareStatement(consulta);
        ResultSet resultado = pstm.executeQuery();
        if (resultado.next()) {
            usuario = new Usuario();
            Perfil perfil = new Perfil();
            usuario.setNome(resultado.getString("u_nome"));
            usuario.setLogin(resultado.getString("login"));
            usuario.setSenha(resultado.getString("senha"));
            usuario.setUltimoAcesso(resultado.getDate("ultimoacesso"));
            usuario.setId(resultado.getInt("u_id"));
            perfil.setId(resultado.getInt("idperfil"));
            perfil.setNome(resultado.getString("p_nome"));
            perfil.setObservacao(resultado.getString("observacao"));
            usuario.setPerfil(perfil);
        } else {
            testSalvar();
        }
        return usuario;
    }

}
