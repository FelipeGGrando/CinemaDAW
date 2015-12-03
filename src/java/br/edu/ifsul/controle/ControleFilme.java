package br.edu.ifsul.controle;
import br.edu.ifsul.dao.AtorDAO;
import br.edu.ifsul.dao.web.FilmeDAO;
import br.edu.ifsul.dao.DiretorDAO;
import br.edu.ifsul.modelo.Ator;
import br.edu.ifsul.modelo.Filme;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 */
@ManagedBean(name = "controleFilme")
@ViewScoped
public class ControleFilme implements Serializable {

    private FilmeDAO<Filme> dao;
    private Filme objeto;
    private AtorDAO daoAtor;
    private Ator ator;
    private DiretorDAO daoDiretor;

    public ControleFilme() {
        dao = new FilmeDAO<Filme>();
        daoAtor = new AtorDAO();
        daoDiretor = new DiretorDAO();
    }

    public void adicionarAtor() {
        if (ator != null) {
            if (!objeto.getAtores().contains(ator)) {
                objeto.getAtores().add(ator);
                Util.mensagemInformacao("Ator adicionado com sucesso");
            } else {
                Util.mensagemErro("Ator já existente na lista");
            }
        }
    }

    public void removerAtor(int idx) {
        Ator a = objeto.getAtores().get(idx);
        objeto.getAtores().remove(a);
        Util.mensagemInformacao("Ator removido");
    }



    public String listar() {
        return "/privado/filme/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Filme();
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

    public void remover(Integer id) {
        try {
            dao.remove(id);
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }
    }

    public FilmeDAO getDao() {
        return dao;
    }

    public void setDao(FilmeDAO dao) {
        this.dao = dao;
    }

    public Filme getObjeto() {
        return objeto;
    }

    public void setObjeto(Filme objeto) {
        this.objeto = objeto;
    }

    public AtorDAO getDaoAtor() {
        return daoAtor;
    }

    public void setDaoAtor(AtorDAO daoAtor) {
        this.daoAtor = daoAtor;
    }

    public Ator getAtor() {
        return ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public DiretorDAO getDaoDiretor() {
        return daoDiretor;
    }

    public void setDaoDiretor(DiretorDAO daoDiretor) {
        this.daoDiretor = daoDiretor;
    }
  
}
