/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Pessoa;
import repository.Pessoas;

/**
 *
 * @author Leonardo
 */
@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter{
    //Cria uma instancia do repositório para ter acesso ao metodo porId
    private Pessoas pessoas = new Pessoas();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        Pessoa retorno = null;
        if(valor != null && !"".equals(valor)){
            retorno = pessoas.porId(new Long(valor));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null){
            Pessoa pessoa = (Pessoa) o;
            return pessoa.getId() == null ? null : pessoa.getId().toString();
        }
        return null;
    }
    
}
