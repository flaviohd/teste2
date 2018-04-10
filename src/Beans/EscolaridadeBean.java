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


@ManagedBean(name="escolaridadeBean")
@ViewScoped
public class EscolaridadeBean implements Serializable{
 
        Escolaridade escolaridade;
        private Escolaridade escolaridadeSelecionado; 
	List escolaridades = new ArrayList();
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

	public EscolaridadeBean(){
		escolaridades = new EscolaridadeDao().listar();
                totalRegistros = escolaridades.size();
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
		new EscolaridadeDao().inserir(escolaridade);
		escolaridades = new EscolaridadeDao().listar();
		//escolaridade = new Escolaridade();
                cadastrando = false;
                editando = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso."));
                } else if(editando){
                new EscolaridadeDao().alterar(escolaridade);
		escolaridades = new EscolaridadeDao().listar();
		//escolaridade = new Escolaridade();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));
                
                }
	}
      
        public void preparaCadastro(){
		escolaridade = new Escolaridade();
              
               // escolaridadeSelecionado = null;
                cadastrando = true;
                editando = false;
                 //System.out.println("Prepara Cadastro");
         
	}
        
        
        public boolean podeEditarExcluir(){
            return !(escolaridadeSelecionado != null);
              
        }
        
        public void preparaAlteracao(){
	      escolaridade = escolaridadeSelecionado;
                 // setEscolaridadeSelecionado(escolaridade);
                 cadastrando = false;
                editando = true;
                 //System.out.println("Prepara Cadastro");
	}           
 
        public int getTotalRegistros() {
          return  escolaridades.size();
        }

        public void setTotalRegistros(int totalRegistros) {
                this.totalRegistros = totalRegistros;
        }
 
	public void alterar(ActionEvent actionEvent){
		new EscolaridadeDao().alterar(escolaridade);
		escolaridades = new EscolaridadeDao().listar();
		//escolaridade = new Escolaridade();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));  
	}
        
	public void excluir(ActionEvent actionEvent){
             System.out.println("executando exclusao!!!");
		new EscolaridadeDao().excluir(escolaridadeSelecionado);
                escolaridadeSelecionado = null;
                escolaridade = null;
		escolaridades = new EscolaridadeDao().listar();
		//escolaridade = new Escolaridade();
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

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}
 
	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}
 
	public List getEscolaridades() {
		return escolaridades;
	}
 
	public void setEscolaridades(List escolaridades) {
		this.escolaridades = escolaridades;
	}
        
        public void setEscolaridadeSelecionado(Escolaridade t){
                escolaridadeSelecionado = t;
        }
   
        public Escolaridade getEscolaridadeSelecionado(){
                return escolaridadeSelecionado;
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
                              
		        Criteria cri = session.createCriteria(Escolaridade.class);
         
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
                               escolaridades = new ArrayList();
                                escolaridades = cri.list();
                                escolaridadeSelecionado = null;
                                } catch(Exception e) {
  
                                      }
                         
      }
      
}