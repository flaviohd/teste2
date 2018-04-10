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


@ManagedBean(name="categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable{
 
        Categoria categoria;
        private Categoria categoriaSelecionado; 
	List categorias = new ArrayList();
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

	public CategoriaBean(){
		categorias = new CategoriaDao().listar();
                totalRegistros = categorias.size();
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
		new CategoriaDao().inserir(categoria);
		categorias = new CategoriaDao().listar();
		//categoria = new Categoria();
                cadastrando = false;
                editando = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso."));
                } else if(editando){
                new CategoriaDao().alterar(categoria);
		categorias = new CategoriaDao().listar();
		//categoria = new Categoria();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));
                
                }
	}
      
        public void preparaCadastro(){
		categoria = new Categoria();
              
               // categoriaSelecionado = null;
                cadastrando = true;
                editando = false;
                 //System.out.println("Prepara Cadastro");
         
	}
        
        
        public boolean podeEditarExcluir(){
            return !(categoriaSelecionado != null);
              
        }
        
        public void preparaAlteracao(){
	      categoria = categoriaSelecionado;
                 // setCategoriaSelecionado(categoria);
                 cadastrando = false;
                editando = true;
                 //System.out.println("Prepara Cadastro");
	}           
 
        public int getTotalRegistros() {
          return  categorias.size();
        }

        public void setTotalRegistros(int totalRegistros) {
                this.totalRegistros = totalRegistros;
        }
 
	public void alterar(ActionEvent actionEvent){
		new CategoriaDao().alterar(categoria);
		categorias = new CategoriaDao().listar();
		//categoria = new Categoria();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));  
	}
        
	public void excluir(ActionEvent actionEvent){
             System.out.println("executando exclusao!!!");
		new CategoriaDao().excluir(categoriaSelecionado);
                categoriaSelecionado = null;
                categoria = null;
		categorias = new CategoriaDao().listar();
		//categoria = new Categoria();
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

	public Categoria getCategoria() {
		return categoria;
	}
 
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
 
	public List getCategorias() {
		return categorias;
	}
 
	public void setCategorias(List categorias) {
		this.categorias = categorias;
	}
        
        public void setCategoriaSelecionado(Categoria t){
                categoriaSelecionado = t;
        }
   
        public Categoria getCategoriaSelecionado(){
                return categoriaSelecionado;
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
                              
		        Criteria cri = session.createCriteria(Categoria.class);
         
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
                               categorias = new ArrayList();
                                categorias = cri.list();
                                categoriaSelecionado = null;
                                } catch(Exception e) {
  
                                      }
                         
      }
      
}