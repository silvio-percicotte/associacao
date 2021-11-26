/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entidade.Usuario;
import java.util.List;

/**
 *
 * @author SESI_SENAI
 */
public interface UsuarioDao extends BaseDao {
    
    Usuario pesquisarPorId(int id) throws Exception;
    
    List<Usuario> pesquisarPorNome(String nome)throws Exception;
    
    Usuario logar(String login, String senha)throws Exception;
}
