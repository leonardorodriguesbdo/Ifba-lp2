package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Pessoa;

/**
 *
 * @author Leonardo
 * Esta classe é responsavel por gerencia os elementos da interface
 * fazendo a interação com o código java (No futuro, nossas regra de negócio)
 */
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
    
    private List<Pessoa> pessoas;
    private Pessoa novaPessoa;
    private Pessoa pessoaSelecionada;

    public PessoaBean() {
        this.pessoas = new ArrayList<>();
        this.novaPessoa = new Pessoa();
        this.pessoaSelecionada = null;
        
        Pessoa p = new Pessoa();
        p.setId(1L); 
        p.setNome("Leo"); 
        //adiciona uma pessoa na lista
        this.pessoas.add(p);
        Pessoa p1 = new Pessoa();
        p1.setId(2L); p1.setNome("cinthia"); pessoas.add(p1);
        Pessoa p2 = new Pessoa();
        p2.setId(3L); p2.setNome("teste"); pessoas.add(p2);
        Pessoa p3 = new Pessoa();
        p3.setId(4L); p3.setNome("serena"); pessoas.add(p3);
    }
    
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (pessoaSelecionada != null){
            this.novaPessoa = new Pessoa();
            pessoaSelecionada = null;
            FacesMessage mensagem = new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                "Pessoa cadastrada com sucesso.");
            context.addMessage(null, mensagem);
        }else{
            
        
            if (!this.novaPessoa.getNome().isEmpty()){
                this.pessoas.add(this.novaPessoa);
                this.novaPessoa = new Pessoa();

                FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                    "Pessoa cadastrada com sucesso.");
                context.addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Erro.",
                    "O nome da pessoa deve ser preenchido.");
                context.addMessage(null, mensagem);
            }
        }
        /*if (this.novaPessoa != null){
            this.pessoas.add(this.novaPessoa);
            this.novaPessoa = new Pessoa();
            
            FacesMessage mensagem = new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                "Pessoa cadastrada com sucesso.");
            context.addMessage(null, mensagem);
        }else{
            this.novaPessoa = new Pessoa();
            FacesMessage mensagem = new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                "Pessoa cadastrada com sucesso.");
            context.addMessage(null, mensagem);
        }*/
        
    }
    
    public void excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
    		
            this.pessoas.remove(this.pessoaSelecionada);
            
            context.addMessage(null, new FacesMessage(
                "Pessoa excluído com sucesso!"));
    	} catch (Exception e) {
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, mensagem);
    	}
    }
    
    public void alterar(){
        if (this.novaPessoa == null)
            this.novaPessoa = new Pessoa();
        
        novaPessoa = pessoaSelecionada;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public Pessoa getNovaPessoa() {
        System.out.println("palavra set: " + novaPessoa.toString());
        return novaPessoa;
    }

    public Pessoa getPessoaSelecionada() {
        System.out.println("palavra set: " + pessoaSelecionada.toString());
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }
    
    
}

