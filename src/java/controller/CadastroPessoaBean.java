/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.Pessoa;
import service.CadastroPessoas;

/**
 * @author Leonardo
 * Esta classe é um Managed Bean, uma classe que gerencia o comportamento da página.
 * Os managed beans nada mais são que Java Beans, que servem como canais entre a
 * interface gráfica (a página) e o back-end da aplicação (regras de negócio, acesso ao
 * banco de dados, etc).
 * Os beans gerenciados do JSF podem receber dados digitados pelos usuários através de
 * alguma página, processar métodos através de ações dos usuários e fornecer dados para
 * apresentação na página.
*/
@ManagedBean
@ViewScoped
public class CadastroPessoaBean {
    //Cria uma instância da classe de service (Regras de négocio), para ter acesso ao back-end da aplicação
    private CadastroPessoas cadastro;
    //Vai receber numa lista de objetos Pessoa, as pessoas cadastradas no BD
    private List<Pessoa> pessoas;
    //cria um objeto do tipo Pessoa para ser "persistido" no BD
    private Pessoa pessoa;
    //Criando uma pessoa temporaria para guardar informações da exclusão
    private Pessoa pessoaSelecionada;
    
    public CadastroPessoaBean(){
        //O construtor da classe inicializa a instancia do servico (CadastroPessoa) para ter acesso
        //aos métodos salvar, excluir, pesquisar, etc...
        cadastro = new CadastroPessoas();
    }
    
    //Instancia uma nova Pessoa quando ela estiver nulo e carrea a lista de pessoas (consultar())
    public void prepararCadastro(){
        if(pessoa == null){
            pessoa = new Pessoa();
        }
        consultar();
    }
    
    /* Carrega a lista das pessoas cadastradas.
    * Para isso acessa o método exibirTodos da camada de servico
    */
    public void consultar(){
        pessoas = cadastro.exibirTodos();
    }

    /* metodos get e set que fazem a interação entre o ManagedBean e a página xhtml */
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    
}
