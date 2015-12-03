package br.edu.ifsul.controle;
import br.edu.ifsul.dao.AtorDAO;
import br.edu.ifsul.modelo.Ator;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleAtor")
@ViewScoped
public class ControleAtor implements Serializable {

    private AtorDAO dao;
    private Ator objeto;

    public ControleAtor() {
        dao = new AtorDAO();
    }

    public String listar() {
        return "/privado/ator/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Ator();
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

    public AtorDAO getDao() {
        return dao;
    }

    public void setDao(AtorDAO dao) {
        this.dao = dao;
    }

    public Ator getObjeto() {
        return objeto;
    }

    public void setObjeto(Ator objeto) {
        this.objeto = objeto;
    }
}
