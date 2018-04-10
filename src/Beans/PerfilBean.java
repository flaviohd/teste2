/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import DAO.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author flavio
 */


@ManagedBean(name="perfilBean")
@ViewScoped
public class PerfilBean implements Serializable{
 
        Perfil perfil;
        private Perfil perfilSelecionado; 
	List perfis = new ArrayList();
        private String filtroCampoId;
        private String filtroCampoDesc;
        private String filtroTipoId;
        private String filtroTipoDesc;
        private boolean filtroVisivel = false;
        private String textoBotaoConsulta = "Exibir filtro";
        private String botaoIcone = "ui-icon ui-icon-arrowthick-1-s"; 
        private int totalRegistros;
        private boolean cadastrando = false;
        private boolean editando = false;

	public PerfilBean(){
		perfis = new PerfilDao().listar();
                totalRegistros = perfis.size();
	}
        
        public boolean isCadastrando() {
        return cadastrando;
        }

        public void setCadastrando(boolean cadastrando) {
        this.cadastrando = cadastrando;
        }

        public boolean isEditando() {
        return editando;
        }

        public void setEditando(boolean editando) {
        this.editando = editando;
        }
 
	public void cadastrar(ActionEvent actionEvent){
                if (cadastrando){
		new PerfilDao().inserir(perfil);
		perfis = new PerfilDao().listar();
		//perfil = new Perfil();
                cadastrando = false;
                editando = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso."));
                } else if(editando){
                new PerfilDao().alterar(perfil);
		perfis = new PerfilDao().listar();
		//perfil = new Perfil();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));
                
                }
	}
      
        public void preparaCadastro(){
		perfil = new Perfil();
              
               // perfilSelecionado = null;
                cadastrando = true;
                editando = false;
                 //System.out.println("Prepara Cadastro");
         
	}
        
        
        public boolean podeEditarExcluir(){
            return !(perfilSelecionado != null);
              
        }
        
        public void preparaAlteracao(){
	      perfil = perfilSelecionado;
                 // setPerfilSelecionado(perfil);
                 cadastrando = false;
                editando = true;
                 //System.out.println("Prepara Cadastro");
	}           
 
        public int getTotalRegistros() {
          return  perfis.size();
        }

        public void setTotalRegistros(int totalRegistros) {
                this.totalRegistros = totalRegistros;
        }
 
	public void alterar(ActionEvent actionEvent){
		new PerfilDao().alterar(perfil);
		perfis = new PerfilDao().listar();
		//perfil = new Perfil();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));  
	}
        
	public void excluir(ActionEvent actionEvent){
             System.out.println("executando exclusao!!!");
		new PerfilDao().excluir(perfilSelecionado);
                perfilSelecionado = null;
                perfil = null;
		perfis = new PerfilDao().listar();
		//perfil = new Perfil();
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro excluído com sucesso."));
	}
        
        public String getFiltroCampoId() {
                return filtroCampoId;
        }

        public void setFiltroCampoId(String filtroCampoId) {
                this.filtroCampoId = filtroCampoId;
        }

        public String getFiltroCampoDesc() {
                return filtroCampoDesc;
        }

        public void setFiltroCampoDesc(String filtroCampoDesc) {
                this.filtroCampoDesc = filtroCampoDesc;
        }

        public String getFiltroTipoId() {
                return filtroTipoId;
        }

        public void setFiltroTipoId(String filtroTipoId) {
                this.filtroTipoId = filtroTipoId;
        }

        public String getFiltroTipoDesc() {
                return filtroTipoDesc;
        }

        public void setFiltroTipoDesc(String filtroTipoDesc) {
                this.filtroTipoDesc = filtroTipoDesc;
        }

        public boolean isFiltroVisivel() {
                return filtroVisivel;
        }

        public void setFiltroVisivel(boolean filtroVisivel) {
                this.filtroVisivel = filtroVisivel;
        }

        public String getTextoBotaoConsulta() {
                return textoBotaoConsulta;
        }

        public void setTextoBotaoConsulta(String textoBotaoConsulta) {
                this.textoBotaoConsulta = textoBotaoConsulta;
        }
   
        public String getBotaoIcone() {
                return botaoIcone;
        }

        public void setBotaoIcone(String botaoIcone) {
                this.botaoIcone = botaoIcone;
        }

	public Perfil getPerfil() {
		return perfil;
	}
 
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
 
	public List getPerfis() {
		return perfis;
	}
 
	public void setPerfis(List perfis) {
		this.perfis = perfis;
	}
        
        public void setPerfilSelecionado(Perfil t){
                perfilSelecionado = t;
        }
   
        public Perfil getPerfilSelecionado(){
                return perfilSelecionado;
        }
    
        public void onRowSelect(SelectEvent event) {
        
        }
        
        public void onRowUnselect(SelectEvent event) {
        
        }
      
        public void alterarExibicaoFiltro(){
          if (filtroVisivel == true){
              filtroVisivel = false;
              textoBotaoConsulta = "Exibir filtro";
              botaoIcone = "ui-icon ui-icon-arrowthick-1-s";
          }else {
              filtroVisivel = true;
               textoBotaoConsulta = "Ocultar filtro";
              botaoIcone = "ui-icon ui-icon-arrowthick-1-n";
          }
          
        }
      
        public void consultar() {
      
            Session session = HibernateUtil.getSessionFactory().openSession();

            System.out.println("filtroTipoId: " + filtroTipoId);
            System.out.println("filtroCampoId: " + filtroCampoId);
            System.out.println("filtroTipoDesc: " + filtroTipoDesc);
            System.out.println("filtroCampoDesc: " + filtroCampoDesc);
           
			try{
                              
		        Criteria cri = session.createCriteria(Perfil.class);
         
                         if(filtroCampoId!=null && !filtroCampoId.isEmpty()){
                          try {
                             Integer numero = Integer.valueOf(filtroCampoId);
                              if(filtroTipoId.equals("e")){
                               cri.add(Restrictions.eq("id",numero));
                              }
                              if(filtroTipoId.equals("c")){
                               cri.add(Restrictions.like("id",numero));
                              }
                           } catch(Exception e){
                            
                           }
                                                }
                       
                       if(filtroCampoDesc!=null && !filtroCampoDesc.isEmpty()){
                  
                        if(filtroTipoDesc.equals("e")){
                              System.out.println("Teste1111111111: ");
                            cri.add(Restrictions.eq("descricao",filtroCampoDesc));
                        }
                        if(filtroTipoDesc.equals("c")){
                            cri.add(Restrictions.like("descricao","%" + filtroCampoDesc + "%"));
                        }
                           
                       }
                               perfis = new ArrayList();
                                perfis = cri.list();
                                perfilSelecionado = null;
                                } catch(Exception e) {
  
                                      }
                         
      }
      
}