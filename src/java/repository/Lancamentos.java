/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Lancamento;
import util.JpaUtil;

/**
 *
 * @author Leonardo
 */
public class Lancamentos {
    private EntityManager manager;
    
    public Lancamentos(){
        //insira o código aqui
	this.manager = JpaUtil.getEntityManager();
    }
    
    //insira o código para realizar as operações CRUD de lançamento aqui
    /*public void adicionar(Lancamento lanca){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.persist(lanca);
        trx.commit();
    }

    //atualizar um registro
    public void atualizar(Lancamento lanca){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.merge(lanca);
        trx.commit();
    }*/
    
    //Este método grava um novo lancamento ou atualiza um lancamento existente
    public void guardar(Lancamento lanca){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.merge(lanca);
        trx.commit();
    }
    
    //Excluir um registro de lancamento
    public void remover(Lancamento lanca){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.remove(lanca);
        trx.commit();
    }
    
    //Busca um lancamento específico do banco
    public Lancamento porId(Long id){
        return manager.find(Lancamento.class, id);
    }
    
    //Busca todos os lançamentos na tabela lancamento
    public List<Lancamento> todos(){
        TypedQuery<Lancamento> query = manager.createQuery(
            "from Lancamento", Lancamento.class);
        return query.getResultList();
    }
}
