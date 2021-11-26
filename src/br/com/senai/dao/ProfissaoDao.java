/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import br.com.senai.entidade.Profissao;
import java.util.List;


public interface ProfissaoDao extends BaseDao{

    Profissao pesquisarPorId(int id) throws Exception;
    
    List<Profissao> pesquisarPorNome(String nome) throws Exception;
    
}
