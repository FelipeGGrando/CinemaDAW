package br.edu.ifsul.controle;
import br.edu.ifsul.dao.FilmeDAO;
import br.edu.ifsul.dao.SalaDAO;
import br.edu.ifsul.dao.SessaoDAO;
import br.edu.ifsul.modelo.Sessao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleSessao")
@ViewScoped
public class ControleSessao implements Serializable {

    private SessaoDAO dao;
    private Sessao objeto;
    private FilmeDAO daoFilme;
    private SalaDAO daoSala;

    public ControleSessao() {
        dao = new SessaoDAO();
        daoFilme = new FilmeDAO();
        daoSala = new SalaDAO();
    }

    public String listar() {
        return "/privado/sessao/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Sessao();
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

    public SessaoDAO getDao() {
        return dao;
    }

    public void setDao(SessaoDAO dao) {
        this.dao = dao;
    }

    public Sessao getObjeto() {
        return objeto;
    }

    public void setObjeto(Sessao objeto) {
        this.objeto = objeto;
    }

    public FilmeDAO getDaoFilme() {
        return daoFilme;
    }

    public void setDaoFilme(FilmeDAO daoFilme) {
        this.daoFilme = daoFilme;
    }

    public SalaDAO getDaoSala() {
        return daoSala;
    }

    public void setDaoSala(SalaDAO daoSala) {
        this.daoSala = daoSala;
    }
    
    
}
