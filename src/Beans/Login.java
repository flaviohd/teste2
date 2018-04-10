/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import static Beans.Criptografia.geraCriptografia;
import DAO.HibernateUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
/**
 *
 * @author flavio
 */
@ManagedBean(name="login")
@SessionScoped
public class Login implements Serializable {
 
    private static final long serialVersionUID = 1094801825228386363L;
     
    private String senha;
    private String msg;
    private String usuario;
    private String novaSenha;
    private String repeteNovaSenha;
    //private String tela;
    private boolean mostraFormAltSenha;
    private boolean mostraFormUsuarioSenha;
    private String cabecalho;
    private Usuario usuarioLogado;
             private static Session sessao;
      MenuBean menu ;
      
      public Login(){
        // tela = "usuarioSenha";
         mostraFormAltSenha = false;
         mostraFormUsuarioSenha = true;
         cabecalho = "Login";
      }
 
    public MenuBean getMenu(){
        return menu;
    }
    
    public void setMenu(MenuBean menu){
        this.menu = menu;
    }
      
      public String getSenha() {
        return senha;
    }
 
    public void setSenha(String senha) {
        this.senha = senha;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getUsuario() {
        return usuario;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getRepeteNovaSenha() {
        return repeteNovaSenha;
    }

    public void setRepeteNovaSenha(String repeteNovaSenha) {
        this.repeteNovaSenha = repeteNovaSenha;
    }

   

    public boolean isMostraFormAltSenha() {
        return mostraFormAltSenha;
    }

    public void setMostraFormAltSenha(boolean mostraFormAltSenha) {
        this.mostraFormAltSenha = mostraFormAltSenha;
    }

    public boolean isMostraFormUsuarioSenha() {
        return mostraFormUsuarioSenha;
    }

    public void setMostraFormUsuarioSenha(boolean mostraFormUsuarioSenha) {
        this.mostraFormUsuarioSenha = mostraFormUsuarioSenha;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    
    
    
    //validate login
    public String validateUsuarioSenha() {
         System.out.println("tentativa de conexao!!");
         HttpSession session = SessionBean.getSession();
         usuarioLogado = new Usuario();
         usuarioLogado.setId(1);
         usuarioLogado.setNomeCompleto("ttetettette");
         usuarioLogado.setNomeUsuario("ssdsdssdsdsd");
         
         session.setAttribute("usuario",usuarioLogado.getNomeUsuario());
         session.setMaxInactiveInterval(10000);
         menu = new MenuBean();
         menu.definirPermissoes("sssssssss");
         mostraFormAltSenha = false;
         mostraFormUsuarioSenha = true;
        return "index";
      /*  try {
           // throw new Exception();
            usuarioLogado = LoginDao.validate(usuario, senha);
            
              if(usuarioLogado!=null && usuarioLogado.getAlterarSenha()=='N'){  
            liberaAcesso();
             mostraFormAltSenha = false;
             mostraFormUsuarioSenha = true;
            return "index";
        } else if (usuarioLogado!=null && usuarioLogado.getAlterarSenha()=='S'){  
             mostraFormAltSenha = true;
             mostraFormUsuarioSenha = false;
             cabecalho = "Alteração de senha";
             System.out.println("Usuario identificado" + usuarioLogado.toString());
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
                            "Nome de usuário ou senha inválidos.") );
            return "login";
        }
            
            
            
        } catch (Exception ex) {
            System.out.println("problema na conexao!!");
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,null,
                      "Erro na inicialização do Hibernate!") );     
          
             return "login";
        }
         
      
         return "login";
         */
    }
 
    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
    
    public void liberaAcesso(){
        HttpSession session = SessionBean.getSession();
            session.setAttribute("usuario", usuarioLogado.getNomeUsuario());
            session.setMaxInactiveInterval(10000);
            menu = new MenuBean();
            menu.definirPermissoes(usuarioLogado.getNomeUsuario());
    }
    
    public String confirmarSenha(){
        System.out.println("Confirmar senha!!!!!");
        System.out.println("Nova senha:" + novaSenha );
        
          System.out.println("Confirmar senha!!!!!");
          liberaAcesso();
          
           String senhaOriginal = usuarioLogado.getSenha();
           String senhaCriptografada = geraCriptografia(senhaOriginal);
           usuarioLogado.setSenha(senhaCriptografada);
           usuarioLogado.setAlterarSenha('N');
           UsuarioDao ud = new UsuarioDao();
           ud.alterar(usuarioLogado);
           
           
     
          System.out.println("Alterou senha usuario!!!!!");
         
         System.out.println("Confirmar senha!!!!!");
          return "index";
    }
    
    public void cancelaAlteracaoSenha(){
          mostraFormAltSenha = false;
          mostraFormUsuarioSenha = true;
          cabecalho = "Login"; 
    }   
   
   
       public void alteraSenha(){
       

          
         
           String senhaCriptografada = geraCriptografia(novaSenha);
           usuarioLogado.setSenha(senhaCriptografada);
           UsuarioDao ud = new UsuarioDao();
           ud.alterar(usuarioLogado);
              FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,null,
                            "A senha foi alterada com sucesso.") );
           
     
       
    }
    
}