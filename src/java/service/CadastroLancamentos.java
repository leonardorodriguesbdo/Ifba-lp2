/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import model.Lancamento;
import repository.Lancamentos;

/**
 *
 * @author Leonardo
 */
public class CadastroLancamentos implements Serializable{
    private Lancamentos lancamentos;
    
    //carregar a instância do repositório de lancamento, para ter acesso ao CRUD 
    public CadastroLancamentos(Lancamentos lancamentos){
        this.lancamentos = lancamentos;
    }
    
    //Persiste o objeto lançamento na tabela do banco de dados
    public void salvar(Lancamento lanc) throws NegocioException{
        //Regra de negócio: Não deixa cadastrar uma data de pagamento futura
        if (lanc.getDataPagamento() != null 
                && lanc.getDataPagamento().after(new Date())){
            throw new NegocioException("A data de pagamento não pode ser futura.");
        }
        this.lancamentos.adicionar(lanc);
    }
    
    //Exclui um lancamento na tabela Lancamento do BD
    public void excluir(Lancamento lanc) throws NegocioException{
        //Regra de negócio: garante que o objeto retornado do banco seja 
        //o mesmo que esta em memoria.
        lanc = this.lancamentos.porId(lanc.getId());
        
        if (lanc.getDataPagamento() != null){
            throw new NegocioException("Não é possível excluir um lancamento pago");
        }
        
        this.lancamentos.remover(lanc);
    }
    
    public Lancamento pesquisarPorId(Long id){
        return this.lancamentos.porId(id);
    }
    
    public List<Lancamento> exibirTodos(){
        return this.lancamentos.todos();
    }
}
