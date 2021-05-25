
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Pessoa;
import repository.Pessoas;
import service.CadastroPessoas;
import service.NegocioException;
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
        //p.setId(5L);
        //p.setNome("Teste exclusao");
        //p.setEmail("teste@ifba.edu.br");
        //p.setContato("7799313232");

        //Semelhante ao INSERT (SQL) vai inserir um novo registro no banco
        //manager.persist(p);
        
        p = manager.find(Pessoa.class, 2L);
        p.setNome("Cinthia Batista");
        manager.persist(p);
        //manager.merge(p);
        //manager.remove(p);   


        //Confirma a transação
        trx.commit();
        
        /*EntityManager manager = JpaUtil.getEntityManager();
        
        //Cria uma instancia do repositório
        Pessoas pessoas = new Pessoas(manager);
        
        //Cria uma instancia de pessoa
        Pessoa p ;//= new Pessoa();
        //p.setNome("Fulano");
        //p.setEmail("fulanalo@ifba.edu");
        //p.setContato("99999999");
        
        //Cria uma instancia da regra de negócio
        /*CadastroPessoas cad = new CadastroPessoas(pessoas);
        try {
            //Salva uma pessoa no banco de dados
            //cad.salvar(p);
            p = cad.pesquisarPorPessoa(4L);
            //p.setId(5L);
            p.setNome("puta merda");
            
            cad.salvar(p);
            
        } catch (NegocioException ex) {
            Logger.getLogger(CriarPessoas.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
    }
    
}
