/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.persistence.Persistence;

/**
 *
 * @author Leonardo
 */
public class CriarTabelas {
    public static void main(String[] args){
        Persistence.createEntityManagerFactory("appfinanceiroPU");
    }
    
}
