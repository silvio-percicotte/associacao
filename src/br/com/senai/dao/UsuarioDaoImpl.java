/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entidade.Perfil;
import br.com.senai.entidade.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SESI_SENAI
 */
public class UsuarioDaoImpl implements UsuarioDao {

    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvar(Object object) throws Exception {
        Usuario usuario = (Usuario) object;
        String sql = "INSERT INTO usuario(nome, login, senha, idperfil) VALUES(?, ?, ? , ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, usuario.getNome());
            preparaInstrucao.setString(2, usuario.getLogin());
            preparaInstrucao.setString(3, usuario.getSenha());
            preparaInstrucao.setInt(4, usuario.getPerfil().getId());
            preparaInstrucao.executeUpdate();
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            usuario.setId(resultado.getInt(1));
        } catch (Exception e) {
            System.out.println("erro ao salvar usuário " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void alterar(Object object) throws Exception {
        Usuario usuario = (Usuario) object;
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?,"
                + " idperfil = ? WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(sql);
            preparaInstrucao.setString(1, usuario.getNome());
            preparaInstrucao.setString(2, usuario.getLogin());
            preparaInstrucao.setString(3, usuario.getSenha());
            preparaInstrucao.setInt(4, usuario.getPerfil().getId());
            preparaInstrucao.setInt(5, usuario.getId());
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao alterar usuário " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(sql);
            preparaInstrucao.setInt(1, id);
            preparaInstrucao.executeUpdate();
        } catch (Exception e) {
            System.out.println("erro ao excluir usuário " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
        }
    }

    @Override
    public Usuario pesquisarPorId(int id) throws Exception {
        Usuario usuario = null;

        String consulta = "SELECT u.id u_id, u.nome u_nome, pe.nome p_nome,"
                + " pe.id p_id, u.*, pe.* from usuario u "
                + " join perfil pe on u.idperfil = pe.id WHERE u.id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setInt(1, id);
            resultado = preparaInstrucao.executeQuery();
            Perfil perfil;
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(id);
                usuario.setNome(resultado.getString("nome"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setUltimoAcesso(resultado.getDate("ultimoacesso"));
                perfil = new Perfil(
                        resultado.getInt("p_id"),
                        resultado.getString("p_nome"),
                        resultado.getString("observacao")
                );
                usuario.setPerfil(perfil);
            }
        } catch (Exception e) {
            System.out.println("erro pesquisar por id " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return usuario;
    }

    @Override
    public Usuario logar(String login, String senha) throws Exception {
        String consulta = "SELECT * FROM usuario WHERE login = ? and senha = ? ";
        Usuario usuario = null;
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, login);
            preparaInstrucao.setString(2, senha);
            resultado = preparaInstrucao.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuario.setUltimoAcesso(resultado.getDate("ultimoacesso"));
            }
        } catch (Exception e) {
            System.out.println("erro ao logar usuário " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return usuario;
    }

    @Override
    public List<Usuario> pesquisarPorNome(String nome) throws Exception {
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();
        String consulta = "SELECT u.id u_id, u.nome u_nome, pe.nome p_nome,"
                + " pe.id p_id, u.*, pe.* from usuario u "
                + " join perfil pe on u.idperfil = pe.id WHERE u.nome LIKE ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(consulta);
            preparaInstrucao.setString(1, "%" + nome + "%");
            resultado = preparaInstrucao.executeQuery();
            Perfil perfil;
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getInt("u_id"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setUltimoAcesso(resultado.getDate("ultimoacesso"));
                perfil = new Perfil(
                        resultado.getInt("p_id"),
                        resultado.getString("p_nome"),
                        resultado.getString("observacao")
                );
                usuario.setPerfil(perfil);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            System.out.println("erro pesquisar por id " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }
        return usuarios;
    }

}
