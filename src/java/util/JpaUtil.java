/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leonardo
 * Cria uma fabrica de conexões que será instaciada uma unica vez - Padrão de projeto Singleton 
 */
public class JpaUtil {
    private static EntityManagerFactory factory;
    
    static{
        factory = Persistence.createEntityManagerFactory("appfinanceiroPU");
    }
    
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
