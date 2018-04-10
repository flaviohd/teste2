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


@ManagedBean(name="trabalhoBean")
@ViewScoped
public class TrabalhoBean implements Serializable{
 
        Trabalho trabalho;
        private Trabalho trabalhoSelecionado; 
	List trabalhos = new ArrayList();
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

	public TrabalhoBean(){
		trabalhos = new TrabalhoDao().listar();
                totalRegistros = trabalhos.size();
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
		new TrabalhoDao().inserir(trabalho);
		trabalhos = new TrabalhoDao().listar();
		//trabalho = new Trabalho();
                cadastrando = false;
                editando = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso."));
                } else if(editando){
                new TrabalhoDao().alterar(trabalho);
		trabalhos = new TrabalhoDao().listar();
		//trabalho = new Trabalho();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));
                
                }
	}
      
        public void preparaCadastro(){
		trabalho = new Trabalho();
              
               // trabalhoSelecionado = null;
                cadastrando = true;
                editando = false;
                 //System.out.println("Prepara Cadastro");
         
	}
        
        
        public boolean podeEditarExcluir(){
            return !(trabalhoSelecionado != null);
              
        }
        
        public void preparaAlteracao(){
	      trabalho = trabalhoSelecionado;
                 // setTrabalhoSelecionado(trabalho);
                 cadastrando = false;
                editando = true;
                 //System.out.println("Prepara Cadastro");
	}           
 
        public int getTotalRegistros() {
          return  trabalhos.size();
        }

        public void setTotalRegistros(int totalRegistros) {
                this.totalRegistros = totalRegistros;
        }
 
	public void alterar(ActionEvent actionEvent){
		new TrabalhoDao().alterar(trabalho);
		trabalhos = new TrabalhoDao().listar();
		//trabalho = new Trabalho();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));  
	}
        
	public void excluir(ActionEvent actionEvent){
             System.out.println("executando exclusao!!!");
		new TrabalhoDao().excluir(trabalhoSelecionado);
                trabalhoSelecionado = null;
                trabalho = null;
		trabalhos = new TrabalhoDao().listar();
		//trabalho = new Trabalho();
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

	public Trabalho getTrabalho() {
		return trabalho;
	}
 
	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}
 
	public List getTrabalhos() {
		return trabalhos;
	}
 
	public void setTrabalhos(List trabalhos) {
		this.trabalhos = trabalhos;
	}
        
        public void setTrabalhoSelecionado(Trabalho t){
                trabalhoSelecionado = t;
        }
   
        public Trabalho getTrabalhoSelecionado(){
                return trabalhoSelecionado;
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
                              
		        Criteria cri = session.createCriteria(Trabalho.class);
         
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
                               trabalhos = new ArrayList();
                                trabalhos = cri.list();
                                trabalhoSelecionado = null;
                                } catch(Exception e) {
  
                                      }
                         
      }
      
}