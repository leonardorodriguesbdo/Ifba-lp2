
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.sound.midi.Soundbank;
import model.Lancamento;
import model.Pessoa;
import model.TipoLancamento;
import repository.Lancamentos;
import repository.Pessoas;
import service.CadastroLancamentos;
import service.CadastroPessoas;
import service.NegocioException;
import util.JpaUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class CriarLancamentos {
    public static void main(String[] args){
        //pegar uma conexao com o bd
        EntityManager manager = JpaUtil.getEntityManager();
        
        //Preparando o campo data para o informação do lancamentos
        Calendar dataVencimento1 = Calendar.getInstance();
        dataVencimento1.set(2020, 2, 15, 0, 0, 0); //formato(ano,mes,dia,hora,minuto,segundo)
        Calendar dataVencimento2 = Calendar.getInstance();
        dataVencimento2.set(2021, 11, 10, 0, 0, 0); //formato(ano,mes,dia,hora,minuto,segundo)
        
        //criando uma nova pessoa
        Pessoa cliente = new Pessoa();
        cliente.setNome("Americanas LTDA");
        cliente.setContato("34411234");
        cliente.setEmail("americanas@mail.com");
        
        Pessoa fornedor = new Pessoa();
        fornedor.setNome("Coelba");
        fornedor.setContato("34334443");
        fornedor.setEmail("coelba@mail.com");
        
        //Cria uma instancia do cadastro de pessoas
        CadastroPessoas cadPessoa = new CadastroPessoas();
        //Cria uma instancia do cadastro de lancamentos
        CadastroLancamentos cadLanc = new CadastroLancamentos(new Lancamentos(manager));
        
        //Criando um lancamento para persistir
        Lancamento lanc1 = new Lancamento();
        lanc1.setDataPagamento(dataVencimento1.getTime());
        lanc1.setDataVencimento(dataVencimento1.getTime());
        lanc1.setDescricao("Venda de licensa de software");
        lanc1.setTipo(TipoLancamento.RECEITA);
        lanc1.setValor(new BigDecimal(5_000));
        lanc1.setPessoa(cliente);
        
        //Criando um segundo lancamento
        Lancamento lanc2 = new Lancamento();
        //lanc2.setDataPagamento(dataVencimento1.getTime());
        lanc2.setDataVencimento(dataVencimento1.getTime());
        lanc2.setDescricao("Conta de energia");
        lanc2.setTipo(TipoLancamento.DESPESA);
        lanc2.setValor(new BigDecimal(500));
        lanc2.setPessoa(fornedor);
        
        
        try {
            //salva primeiro o cliente no banco dados
            cadPessoa.salvar(cliente);
            cadLanc.salvar(lanc1);
            //cadLanc.salvar(lanc2);
        } catch (NegocioException ex) {
            Logger.getLogger(CriarLancamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //exibir uma listagem dos lancamentos
        System.out.println("-------------------------------------------");
        List<Lancamento> lstLanc = cadLanc.exibirTodos();
        System.out.println("id - descrição - pessoa - tipo - valor");
        lstLanc.stream().forEach(lancamento -> System.out.println(lancamento.getId() + " - " +
                                                                lancamento.getDescricao() +  " - " +
                                                                lancamento.getPessoa() + " - " +
                                                                lancamento.getTipo() + " - " +
                                                                lancamento.getValor()
                                                                ));
        System.out.println("-------------------------------------------");
    }
}
