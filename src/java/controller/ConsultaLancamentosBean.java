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
import service.CadastroLancamentos;
import service.NegocioException;

/**
 *
 * @author Leonardo
 */
@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean {
    //Recebe a lista de lanmentos vindo do banco de dados
    private List<Lancamento> lancamentos;
    private CadastroLancamentos cadastro;
    private Lancamento lancamentoSelecionado;
    
    public ConsultaLancamentosBean(){
        cadastro = new CadastroLancamentos();
    }
    
    public void consultar(){
        lancamentos = cadastro.exibirTodos();
    }
    
    public void excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            //remove a lancamento do banco de dados
            cadastro.excluir(lancamentoSelecionado);
            consultar();
            context.addMessage(null, new FacesMessage("Lancamento excluída com sucesso!"));
        }catch(NegocioException e){
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
        }
    }

    public Lancamento getLancamentoSelecionado() {
        return lancamentoSelecionado;
    }

    public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
        this.lancamentoSelecionado = lancamentoSelecionado;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }
    
}
