package br.edu.ifsul.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
public class Util {

    public static void mensagemInformacao(String strMensagem) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, strMensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }
    
    public static void mensagemErro(String strMensagem) {
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, strMensagem, "");
        FacesContext.getCurrentInstance().addMessage(null, mensagem);
    }    
}
