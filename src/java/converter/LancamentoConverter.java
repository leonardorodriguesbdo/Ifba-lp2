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
import model.Lancamento;
import repository.Lancamentos;

/**
 *
 * @author Leonardo
 */
@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter{
    //Cria uma instancia do repositório para ter acesso ao metodo porId
    private Lancamentos lancamentos  = new Lancamentos();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String valor) {
        Lancamento retorno = null;
        if(valor != null && !"".equals(valor)){
            retorno = lancamentos.porId(new Long(valor));
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o != null){
            Lancamento lanc = (Lancamento) o;
            return lanc.getId() == null ? null : lanc.getId().toString();
        }
        return null;
    }
    
}
