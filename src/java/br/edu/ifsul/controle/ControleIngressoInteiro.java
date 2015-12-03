package br.edu.ifsul.controle;
import br.edu.ifsul.dao.IngressoInteiroDAO;
import br.edu.ifsul.dao.SessaoDAO;
import br.edu.ifsul.modelo.IngressoInteiro;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleIngressoInteiro")
@ViewScoped
public class ControleIngressoInteiro implements Serializable {

    private IngressoInteiroDAO dao;
    private IngressoInteiro objeto;
    private SessaoDAO daoSessao;

    public ControleIngressoInteiro() {
        dao = new IngressoInteiroDAO();
        daoSessao = new SessaoDAO();
    }

    public String listar() {
        return "/privado/ingresso_inteiro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new IngressoInteiro();
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso");
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }
    }

    public void editar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }
    }
    
    public void remover(Integer id){
        try {
            dao.remove(id);
        } catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
    }

    public IngressoInteiroDAO getDao() {
        return dao;
    }

    public void setDao(IngressoInteiroDAO dao) {
        this.dao = dao;
    }

    public IngressoInteiro getObjeto() {
        return objeto;
    }

    public void setObjeto(IngressoInteiro objeto) {
        this.objeto = objeto;
    }

    public SessaoDAO getDaoSessao() {
        return daoSessao;
    }

    public void setDaoSessao(SessaoDAO daoSessao) {
        this.daoSessao = daoSessao;
    }
    
}
