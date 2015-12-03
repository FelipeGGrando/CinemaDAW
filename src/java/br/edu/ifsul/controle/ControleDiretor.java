package br.edu.ifsul.controle;
import br.edu.ifsul.dao.DiretorDAO;
import br.edu.ifsul.modelo.Diretor;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleDiretor")
@ViewScoped
public class ControleDiretor implements Serializable {

    private DiretorDAO dao;
    private Diretor objeto;

    public ControleDiretor() {
        dao = new DiretorDAO();
    }

    public String listar() {
        return "/privado/diretor/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Diretor();
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

    public DiretorDAO getDao() {
        return dao;
    }

    public void setDao(DiretorDAO dao) {
        this.dao = dao;
    }

    public Diretor getObjeto() {
        return objeto;
    }

    public void setObjeto(Diretor objeto) {
        this.objeto = objeto;
    }
}
