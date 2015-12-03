package br.edu.ifsul.controle;
import br.edu.ifsul.dao.FuncionarioDAO;
import br.edu.ifsul.dao.SalaDAO;
import br.edu.ifsul.modelo.Sala;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "controleSala")
@ViewScoped
public class ControleSala implements Serializable {

    private SalaDAO dao;
    private Sala objeto;
    private FuncionarioDAO daoFuncionario;

    public ControleSala() {
        dao = new SalaDAO();
        daoFuncionario = new FuncionarioDAO();
    }

    public String listar() {
        return "/privado/sala/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Sala();
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

    public SalaDAO getDao() {
        return dao;
    }

    public void setDao(SalaDAO dao) {
        this.dao = dao;
    }

    public Sala getObjeto() {
        return objeto;
    }

    public void setObjeto(Sala objeto) {
        this.objeto = objeto;
    }

    public FuncionarioDAO getDaoFuncionario() {
        return daoFuncionario;
    }

    public void setDaoFuncionario(FuncionarioDAO daoFuncionario) {
        this.daoFuncionario = daoFuncionario;
    }
    
    
}
