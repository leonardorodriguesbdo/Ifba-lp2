/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Pessoa;
import util.JpaUtil;

/**
 *
 * @author Leonardo
 * Implemeta o CRUD da nossa aplicação
 */
public class Pessoas implements Serializable{
    private EntityManager manager;
    
    /* O contrutor do repositório inicializa o EntityManager pegando uma conexao
    * do banco de dados na classe JpaUtil
    */
    public Pessoas(){
        this.manager = JpaUtil.getEntityManager();
    }
    
    public void adicionar(Pessoa pessoa){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.persist(pessoa); //incluir uma pessoa no banco de dados
        trx.commit();
    }
    
    public void atualizar(Pessoa pessoa){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.merge(pessoa);
        trx.commit();
    }
    
    public void remover(Pessoa pessoa){
        EntityTransaction trx = manager.getTransaction();
        trx.begin();
        manager.remove(pessoa);
        trx.commit();
    }
    
    public Pessoa porId(Long id){
        return manager.find(Pessoa.class, id);
    }
    
    /* Executa uma consulta na tabela pessoas, no banco de dados, e retorna
    * em uma lista de objetos Pessoas
    */ 
    public List<Pessoa> todos(){
        TypedQuery<Pessoa> query = manager.createQuery(
            "from Pessoa", Pessoa.class);
        return query.getResultList();
    }
}
