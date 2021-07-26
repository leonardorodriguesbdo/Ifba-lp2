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
    //lista de pessoas para ser apresentada
    private List<Pessoa> pessoas;
    //cria um objeto do tipo pessoa para ser "persistido"
    private Pessoa novaPessoa;
    //Criando uma pessoa temporaria para guardar informações da exclusão
    private Pessoa pessoaSelecionada;
    
    
    
    //Construtor da classe carrega algumas pessoas na lista
    public PessoaBean() {
        this.pessoas = new ArrayList<>();
        this.novaPessoa = new Pessoa(); //Prepara o objeto para receber informações
        
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
    
    //Salva uma pessoa no banco de dados, neste caso especifico esta adicionando 
    //na lista de pessoas ( private List<Pessoa> pessoas; )
    public void salvar(){
        FacesContext context = FacesContext.getCurrentInstance();
        //corrigindo o metodo para suportar a alteração
        if (this.pessoaSelecionada != null){
            //salvando no BD ... e seta o valor para null para permitir uma nova gravação de pessoa
            this.pessoaSelecionada = null;
            //Faz a limpeza dos campos inputText da tela
            this.novaPessoa = new Pessoa();
        }else{ 
            //Se o ID da pessoa não estiver preenchido não adiciona o nome na lista
            if (this.novaPessoa.getId() != null){
                //adiciona na lista ou no futuro irá chamar o método que salva no banco de dados
                this.pessoas.add(this.novaPessoa);
                //instacia uma nova pessoa e limpa a tela.
                this.novaPessoa = new Pessoa();

                FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                    "Pessoa cadastrada com sucesso.");
                context.addMessage(null, mensagem);
            }else{
                this.novaPessoa = new Pessoa();
                FacesMessage mensagem = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
                    "O campo ID não podem ficar em branco.");
                context.addMessage(null, mensagem);
            }
        }
    }
    
    //Exclui um nome da lista, servirá no futuro para excluir do banco de dados
    public void excluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            //remove a pessoa da lista ( private List<Pessoa> pessoas; )
            this.pessoas.remove(this.pessoaSelecionada);
            context.addMessage(null, new FacesMessage("Pessoa excluída com sucesso!"));
        }catch(Exception e){
            FacesMessage mensagem = new FacesMessage(e.getMessage());
            mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, new FacesMessage("Erro na exclusão!"));
        }
    }
    
    //prepara os dados da pessoa para alteração
    public void alterar(){
        novaPessoa = pessoaSelecionada;
    }
    
    //retorna a lista de pessoas para preencher o componente DataTable
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    //retorna as informações de uma nova pessoa
    public Pessoa getNovaPessoa() {
        return novaPessoa;
    } 

    //seta as informações de uma nova pessoa
    public void setNovaPessoa(Pessoa novaPessoa) {
        this.novaPessoa = novaPessoa;
    }
    
    //retorna as informações de pessoa selecionada
    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    //seta as informações de pessoaSelecionada
    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }
    
    
    
}

