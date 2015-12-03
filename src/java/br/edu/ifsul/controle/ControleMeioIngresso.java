package br.edu.ifsul.controle;
import br.edu.ifsul.dao.MeioIngressoDAO;
import br.edu.ifsul.dao.SessaoDAO;
import br.edu.ifsul.modelo.MeioIngresso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleMeioIngresso")
@ViewScoped
public class ControleMeioIngresso implements Serializable {

    private MeioIngressoDAO dao;
    private MeioIngresso objeto;
    private SessaoDAO daoSessao;

    public ControleMeioIngresso() {
        dao = new MeioIngressoDAO();
        daoSessao = new SessaoDAO();
    }

    public String listar() {
        return "/privado/meio_ingresso/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new MeioIngresso();
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

    public MeioIngressoDAO getDao() {
        return dao;
    }

    public void setDao(MeioIngressoDAO dao) {
        this.dao = dao;
    }

    public MeioIngresso getObjeto() {
        return objeto;
    }

    public void setObjeto(MeioIngresso objeto) {
        this.objeto = objeto;
    }

    public SessaoDAO getDaoSessao() {
        return daoSessao;
    }

    public void setDaoSessao(SessaoDAO daoSessao) {
        this.daoSessao = daoSessao;
    }
    
}
