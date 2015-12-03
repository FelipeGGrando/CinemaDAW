package br.edu.ifsul.controle;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.AcessoUsuario;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private UsuarioDAO dao;
    private Usuario usuarioLogado;
    private String usuario;
    private String senha;

    public ControleLogin() {
        dao = new UsuarioDAO();
    }
    
    public String paginaLogin(){
        return "/login?faces-redirect=true";
    }
    
    public String login(){
        if(dao.login(usuario, senha)){
            usuarioLogado = dao.localizarPorNomeUsuario(usuario);
            FacesContext contexto = FacesContext.getCurrentInstance();
            // Captura a requisição do contexto 
            HttpServletRequest request = (HttpServletRequest)
                     contexto.getExternalContext().getRequest();
            // cria um acesso capturando o ip remoto
            AcessoUsuario acesso = new AcessoUsuario(request.getRemoteAddr());
            usuarioLogado.adicionarAcesso(acesso);
            try {
                dao.merge(usuarioLogado);
            } catch (Exception e){
                Util.mensagemErro("Erro ao persistir acesso: "+e.getMessage());                
            }
            Util.mensagemInformacao("Login efetuado com sucesso");
            return "/index";
        } else {
            Util.mensagemErro("Usuario ou senha inválidos");
            return "/login";
        }
    }
    
    public String logout(){
        usuarioLogado = null;
        Util.mensagemInformacao("Logout efetuado com sucesso");
        return "/index";
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}