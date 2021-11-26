package br.com.senai.dao;

import br.com.senai.entidade.Perfil;
import java.util.List;


/**
 *
 * @author SESI_SENAI
 */
public interface PerfilDao extends BaseDao{

    List<Perfil> pesquisarTodo()throws Exception;
}
