
package br.com.senai.dao;

/**
 *
 * @author SESI_SENAI
 */
public interface BaseDao {
    
    void salvar(Object object) throws Exception;
    
    void alterar(Object object) throws Exception;
    
    void excluir(int id) throws Exception; 
    
}
