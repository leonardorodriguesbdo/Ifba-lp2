/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Lancamento;
import model.Pessoa;
import service.CadastroLancamentos;
import service.CadastroPessoas;
import service.NegocioException;

/**
 *
 * @author Leonardo
 */
@ManagedBean
@ViewScoped
public class CadastroLancamentoBean {
    private CadastroLancamentos cadastro = new CadastroLancamentos();
    private Lancamento lancamento = new Lancamento();
    
    private CadastroPessoas cadPessoas = new CadastroPessoas();
    private List<Pessoa> todasPessoas;
    
    //carregar a lista de pessoas cadastradas no banco
    public void prepararCadastro(){
        todasPessoas = cadPessoas.exibirTodos();
    }
    
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{ 
            cadastro.salvar(lancamento);
            //Prepara para cadastrar um novo lancamento
            lancamento = new Lancamento();
            
            FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                    "Lancamento cadastrada com sucesso.");
                context.addMessage(null, mensagem);
        }catch(NegocioException e){
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public List<Pessoa> getTodasPessoas() {
        return todasPessoas;
    }
    
}
