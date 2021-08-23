/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Leonardo
 * Classe qua vai direcionar as excessões do nosso projeto
 */
public class NegocioException extends Exception{

    public NegocioException(String msg) {
        super(msg);
    }
   
}
