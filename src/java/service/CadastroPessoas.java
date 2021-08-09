/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Pessoa;
import repository.Pessoas;

/**
 *
 * @author Leonardo
 */
public class CadastroPessoas {
    public Pessoas pessoas;

    public CadastroPessoas(){
        //o construtor da classe inicializa o repositório de pessoas
        this.pessoas = new Pessoas();
    }   
    
    public void salvar(Pessoa pessoa) throws NegocioException{
        if(pessoa.getNome() == null){
            throw new NegocioException("O nome não pode ficar em branca");
        }        
        this.pessoas.adicionar(pessoa);
    }
    
    public void excluir(Pessoa p) throws NegocioException{
        p = this.pessoas.porId(p.getId());
        if (p.getId() == null){
            throw new NegocioException("Registro não encontrado no banco");
        }
        this.pessoas.remover(p);
    }
    
    /* Este metódo chama o método exibirTodos do repositório e retorna a listagem
    *  das pessoas cadastradas no banco de dados
    */
    public List<Pessoa> exibirTodos(){
        return this.pessoas.todos();
    }
    
    public Pessoa pesquisarPorPessoa(Long id){
        return this.pessoas.porId(id);
    }
}
