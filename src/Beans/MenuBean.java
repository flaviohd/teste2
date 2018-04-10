/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author flavio
 */

@ManagedBean
@ViewScoped
public final  class  MenuBean  {
    private static MenuBean instancia;
/*
    public static MenuBean getInstancia() {
        return instancia;
    }
*/
    public static void setInstancia(MenuBean instancia) {
        MenuBean.instancia = instancia;
    }
    private String pagina;
    private String paginaAtual;

   
    private MenuModel modelo;
    private Session session;
    private Perfil permissoes;
  
   
    public MenuBean() {
     
         System.out.println("Construtor" + this.hashCode());
        // pagina = "usuarioSenha";
         definirPermissoes("ssss");
    }
    
    public static MenuBean getInstancia(){
       if (instancia==null){
           instancia = new MenuBean();
            System.out.println("Objeto MenuBean: " + instancia.hashCode());
       }
       return instancia;
     }
    
     public String getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(String paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
    
    public Perfil getCategoria(){
        return permissoes;
    }
    
    public void setCategoria(Perfil permissoes){
       this.permissoes = permissoes; 
    }
    
   
    public void definirPermissoes(String usuario){
        modelo = new DefaultMenuModel();
      
        
        session = HibernateUtil.getSessionFactory().openSession();
 
      
        
          DefaultSubMenu firstSubmenu = new DefaultSubMenu("ACADÊMICO");
          
   firstSubmenu.setStyleClass("WhiteIcon");
         firstSubmenu.setIcon("fa fa-bullhorn");
       
        /*  
         if (permissoes.getPerm_menu_Familia()=='S'){
         */
        DefaultMenuItem item = new DefaultMenuItem("Categorias");
        //item.setIcon("ui-icon-disk");
        item.setCommand("#{menuBean.setPagina('Categorias','principal','menu')}");
        item.setUpdate(":formularios");
        item.setIcon("fa fa-archive");
        item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        firstSubmenu.addElement(item);
        
        // }
       // if (permissoes.getPerm_menu_Genero()=='S'){
        item = new DefaultMenuItem("Escolaridades");
        //item.setIcon("ui-icon-disk");
        item.setCommand("#{menuBean.setPagina('Escolaridades','principal','menu')}");
        item.setUpdate(":formularios");
        item.setIcon("fa fa-object-group");
          item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        firstSubmenu.addElement(item);
       // }
        
        //if (permissoes.getPerm_menu_Especie()=='S'){
       item = new DefaultMenuItem("Participantes");
        //item.setIcon("ui-icon-disk");
        item.setCommand("#{menuBean.setPagina('Participantes','principal','menu')}");
        item.setUpdate(":formularios");
        item.setIcon("fa fa-tags");
          item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        firstSubmenu.addElement(item);
      //  }
        
       //  if (permissoes.getPerm_menu_Exsicata()=='S'){
        item = new DefaultMenuItem("Trabalhos");
        //item.setIcon("ui-icon-disk");
        item.setCommand("#{menuBean.setPagina('Trabalhos','principal','menu')}");
        item.setUpdate(":formularios");
        item.setIcon("fa fa-user-plus");
          item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        firstSubmenu.addElement(item);
        // }

        
        modelo.addElement(firstSubmenu);
    
 

        DefaultSubMenu secondSubmenu = new DefaultSubMenu("ADMINISTRAÇÃO");
 
         secondSubmenu.setIcon("fa fa-cog");

          
        // if (permissoes.getPerm_menu_Usuario()=='S'){
         item = new DefaultMenuItem("Usuários");
        item.setCommand("#{menuBean.setPagina('Usuarios','principal','menu')}");
        item.setUpdate(":formularios");
        item.setIcon("fa fa-users");
          item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        secondSubmenu.addElement(item);
        // }
  
        //  if (permissoes.getPerm_menu_Categoria()=='S'){
         item = new DefaultMenuItem("Perfis");
        item.setCommand("#{menuBean.setPagina('Perfis','principal','menu')}");
        item.setUpdate(":formularios");
        item.setIcon("fa fa-folder");
          item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        secondSubmenu.addElement(item);

         
        modelo.addElement(secondSubmenu);
         

        
         DefaultSubMenu thirdSubmenu = new DefaultSubMenu("UTILITÁRIOS");
        
             thirdSubmenu.setIcon("fa fa-wrench");

       item = new DefaultMenuItem("Alterar senha");
       
        
        
         item.setCommand("#{menuBean.setPagina('Alteracao de senha','principal','menu')}");
        item.setUpdate(":formularios");
         item.setIcon("fa fa-key");
          item.setOnstart("PF('statusDialog').show()");
        item.setOncomplete("PF('statusDialog').hide()");
        item.setUpdate(":formularios");
        thirdSubmenu.addElement(item);
        
        modelo.addElement(thirdSubmenu);
        
     
        
    }
 
    public MenuModel getModelo() {
        return modelo;
    }
    
    
    public  String getPagina(){
        return pagina;
    }
    
    public void setPagina(String pagina, String tipo, String origem){
        if (pagina.equals(this.paginaAtual) && (origem.equals("menu"))){
           FacesContext.getCurrentInstance().addMessage
        (null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "A página " + paginaAtual+ " já está aberta."));
        }
        else 
        {
        	System.out.println("Página" + paginaAtual);
             switch(pagina){
             case "Perfis":
              paginaAtual = "Perfis";
              break;
              case "Escolaridades":
              paginaAtual = "Escolaridades";
              break;
              case "Usuarios":
              paginaAtual = "Usuarios";
              break;
              case "Categorias":
              paginaAtual = "Categorias";
              break;
              case "Participantes":
              paginaAtual = "Participantes";
              break;

             
             
              case "Alteracao de senha":
              paginaAtual = "Alteracao de senha";
              break;
    
             }
            /*
             if (origem.equals("menu")){
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("familiaBean");
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("generoBean");
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("especieBean");
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("exsicataBean");
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("usuarioBean");
            FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("categoriaBean");
           FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("AuditoriaConsultaBean");
             } 
             */
             switch(tipo){
             case "principal":
              pagina = "lista" + pagina;
              break;
              case "detalhe":
              pagina = "frm" + pagina;
              break;
             }
             
             if (paginaAtual.equals("Alteracao de senha"))
                  pagina = "frmAlteracaoSenha";
            this.pagina = pagina;
         
            
                    
        }
    }
    
 
    
}
