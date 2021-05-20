
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Pessoa;
import util.JpaUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class CriarPessoas {
    public static void main(String[] args){
        //Retorna um objeto EntityManager para dar acesso aos metodos CRUD do JPA
        EntityManager manager = JpaUtil.getEntityManager();
        //Cria uma transação
        EntityTransaction trx = manager.getTransaction();
        //Inicia a transação
        trx.begin();

        //Criar um objeto do tipo pessoa que será persistido no banco
        Pessoa p = new Pessoa();
        //p.setNome("Teste exclusao");
        //p.setEmail("teste@ifba.edu.br");
        //p.setContato("7799313232");

        //Semelhante ao INSERT (SQL) vai inserir um novo registro no banco
        //manager.persist(p);
        
        p = manager.find(Pessoa.class, 3L);
        //p.setNome("Cinthia Batista");
        //manager.merge(p);
        manager.remove(p);   


        //Confirma a transação
        trx.commit();
    }
    
}
