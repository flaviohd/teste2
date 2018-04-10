/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


/*import static Beans.Criptografia.geraCriptografia;*/
import DAO.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
/**
 *
 * @author flavio
 */


@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{
 
        Usuario usuario;
        private Usuario usuarioSelecionado; 
	List usuarios = new ArrayList(); 
        private String filtroCampoId;
        private String filtroCampoNomeUsuario;
        private String filtroCampoNomeCompleto;
        private String filtroCampoEmail;
        private Perfil filtroCampoPerfil;
         private String filtroCampoDataCriacaoInicial;
          private String filtroCampoDataCriacaoFinal;
 private String filtroCampoAtivo;
        private String filtroTipoId;
        private String filtroTipoNomeUsuario;
        private String filtroTipoNomeCompleto;
        private String filtroTipoEmail;
         private String filtroTipoDataCriacao;
  
        private boolean filtroVisivel = false;
        private String textoBotaoConsulta = "Exibir filtro";
        private String botaoIcone = "ui-icon ui-icon-arrowthick-1-s";
        private int totalRegistros;
        private boolean cadastrando = false;
        private boolean editando = false;

        
   public UsuarioBean(){
		usuarios = new UsuarioDao().listar();
                totalRegistros = usuarios.size();
                
                
	}
    public boolean estaEditando(){
        return editando;
    }  
    
   
  

    public String getFiltroCampoNomeUsuario() {
        return filtroCampoNomeUsuario;
    }

    public void setFiltroCampoNomeUsuario(String filtroCampoNomeUsuario) {
        this.filtroCampoNomeUsuario = filtroCampoNomeUsuario;
    }

    public String getFiltroCampoNomeCompleto() {
        return filtroCampoNomeCompleto;
    }

    public void setFiltroCampoNomeCompleto(String filtroCampoNomeCompleto) {
        this.filtroCampoNomeCompleto = filtroCampoNomeCompleto;
    }

    public String getFiltroCampoEmail() {
        return filtroCampoEmail;
    }

    public void setFiltroCampoEmail(String filtroCampoEmail) {
        this.filtroCampoEmail = filtroCampoEmail;
    }

    public Perfil getFiltroCampoCategoria() {
        return filtroCampoPerfil;
    }

    public void setFiltroCampoCategoria(Perfil filtroCampoCategoria) {
        this.filtroCampoPerfil = filtroCampoCategoria;
    }

    public String getFiltroCampoAtivo() {
        return filtroCampoAtivo;
    }

    public void setFiltroCampoAtivo(String filtroCampoAtivo) {
        this.filtroCampoAtivo = filtroCampoAtivo;
    }

    public String getFiltroTipoNomeUsuario() {
        return filtroTipoNomeUsuario;
    }

    public void setFiltroTipoNomeUsuario(String filtroTipoNomeUsuario) {
        this.filtroTipoNomeUsuario = filtroTipoNomeUsuario;
    }

    public String getFiltroTipoNomeCompleto() {
        return filtroTipoNomeCompleto;
    }

    public void setFiltroTipoNomeCompleto(String filtroTipoNomeCompleto) {
        this.filtroTipoNomeCompleto = filtroTipoNomeCompleto;
    }

    public String getFiltroTipoEmail() {
        return filtroTipoEmail;
    }

    public void setFiltroTipoEmail(String filtroTipoEmail) {
        this.filtroTipoEmail = filtroTipoEmail;
    }

    public String getFiltroCampoDataCriacaoInicial() {
        return filtroCampoDataCriacaoInicial;
    }

    public void setFiltroCampoDataCriacaoInicial(String filtroCampoDataCriacaoInicial) {
        this.filtroCampoDataCriacaoInicial = filtroCampoDataCriacaoInicial;
    }

    public String getFiltroCampoDataCriacaoFinal() {
        return filtroCampoDataCriacaoFinal;
    }

    public void setFiltroCampoDataCriacaoFinal(String filtroCampoDataCriacaoFinal) {
        this.filtroCampoDataCriacaoFinal = filtroCampoDataCriacaoFinal;
    }

    public String getFiltroTipoDataCriacao() {
        return filtroTipoDataCriacao;
    }

    public void setFiltroTipoDataCriacao(String filtroTipoDataCriacao) {
        this.filtroTipoDataCriacao = filtroTipoDataCriacao;
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
            String senhaOriginal;
            String senhaCriptografada;
                if (cadastrando){
                 /* usuario.setDataCriacao(new Date());*/
                  usuario.setQtdTentativasAcesso(0);
                  
                  senhaOriginal = usuario.getSenha();
                  senhaCriptografada = senhaOriginal;
                  usuario.setSenha(senhaCriptografada);
                  System.out.println("USUARIO" + usuario.toString());
		new UsuarioDao().inserir(usuario);
		usuarios = new UsuarioDao().listar();
                cadastrando = false;
                editando =  true;
                 usuarioSelecionado = usuario ;
                 System.out.println("Teste");
                 
                 
                
                 
                 
                 
                System.out.println("Realizado cadastro");
                System.out.println("Cadastrando: " + cadastrando);
                System.out.println("Editando: " + editando);
                
		//usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso."));
                } else if(editando){
                	
                	
                    new UsuarioDao().alterar(usuarioSelecionado);
		usuarios = new UsuarioDao().listar();
		//usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));
                
                }
	}
      
        public void preparaCadastro(){
		usuario = new Usuario();
                cadastrando = true;
                editando = false;
                System.out.println("Prepara cadastro");
                System.out.println("Cadastrando: " + cadastrando);
                System.out.println("Editando: " + editando);
                
               // System.out.println("Departamentos: " + usuario.getDepartamentos().toString());
         
	}
        
        
        public boolean podeEditarExcluir(){
            return !(usuarioSelecionado != null);
              
        }
        
        public void preparaAlteracao(){
		usuario = usuarioSelecionado;
                 cadastrando = false;
                editando = true;
                 System.out.println("Prepara altera��o");
                 System.out.println("Cadastrando: " + cadastrando);
                 System.out.println("Editando: " + editando);
         
	}
   
        public int getTotalRegistros() {
          return  usuarios.size();
        }

        public void setTotalRegistros(int totalRegistros) {
                this.totalRegistros = totalRegistros;
        }
 
	public void alterar(ActionEvent actionEvent){
		new UsuarioDao().alterar(usuarioSelecionado);
		usuarios = new UsuarioDao().listar();
		//usuario = new Usuario();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro alterado com sucesso."));
                
	}
        
	public void excluir(ActionEvent actionEvent){
             System.out.println("executando exclusao!!!");
		new UsuarioDao().excluir(usuarioSelecionado);
		usuarios = new UsuarioDao().listar();
		usuario = new Usuario();
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro excluído com sucesso."));
	}

        
        public String getFiltroCampoId() {
                return filtroCampoId;
        }

        public void setFiltroCampoId(String filtroCampoId) {
                this.filtroCampoId = filtroCampoId;
        }


        public String getFiltroTipoId() {
                return filtroTipoId;
        }

        public void setFiltroTipoId(String filtroTipoId) {
                this.filtroTipoId = filtroTipoId;
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

	public Usuario getUsuario() {
		return usuario;
	}
 
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
 
	public List getUsuarios() {
		return usuarios;
	}
 
	public void setUsuarios(List usuarios) {
		this.usuarios = usuarios;
	}
        
        public void setUsuarioSelecionado(Usuario t){
                usuarioSelecionado = t;
        }
   
        public Usuario getUsuarioSelecionado(){
                return usuarioSelecionado;
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
          
           
			try{
                              
		        Criteria cri = session.createCriteria(Usuario.class);
         
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
                       
                       if(filtroCampoNomeUsuario!=null && !filtroCampoNomeUsuario.isEmpty()){
                  
                        if(filtroTipoNomeUsuario.equals("e")){
                              System.out.println("Teste1111111111: ");
                            cri.add(Restrictions.eq("nomeUsuario",filtroCampoNomeUsuario));
                        }
                        if(filtroTipoNomeUsuario.equals("c")){
                            cri.add(Restrictions.like("nomeUsuario","%" + filtroCampoNomeUsuario + "%"));
                        }
                           
                       }
                       
                       if(filtroCampoNomeCompleto!=null && !filtroCampoNomeCompleto.isEmpty()){
                  
                        if(filtroTipoNomeCompleto.equals("e")){
                              System.out.println("Teste1111111111: ");
                            cri.add(Restrictions.eq("nomeCompleto",filtroCampoNomeCompleto));
                        }
                        if(filtroTipoNomeCompleto.equals("c")){
                            cri.add(Restrictions.like("nomeCompleto","%" + filtroCampoNomeCompleto + "%"));
                        }
                           
                       }

                       if(filtroCampoEmail!=null && !filtroCampoEmail.isEmpty()){
                  
                        if(filtroTipoEmail.equals("e")){
                              System.out.println("Teste1111111111: ");
                            cri.add(Restrictions.eq("email",filtroCampoEmail));
                        }
                        if(filtroTipoEmail.equals("c")){
                            cri.add(Restrictions.like("email","%" + filtroCampoEmail + "%"));
                        }
                           
                       }
                       
                        if(filtroCampoPerfil!=null){
                  
                           
                            cri.add(Restrictions.eq("categoria",filtroCampoPerfil));
                        }
                             
                        if(filtroCampoDataCriacaoInicial!=null && !filtroCampoDataCriacaoInicial.isEmpty()
                                && filtroTipoDataCriacao.equals("e")){ 
                               
                            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
                            Date dataCriacao = dateFormat.parse(filtroCampoDataCriacaoInicial);
                            cri.add(Restrictions.eq("dataCriacao",filtroCampoPerfil));
                                
                        }
                        
                        if(filtroCampoDataCriacaoInicial!=null && !filtroCampoDataCriacaoInicial.isEmpty()
                                && filtroCampoDataCriacaoFinal!=null && !filtroCampoDataCriacaoFinal.isEmpty()
                               && filtroTipoDataCriacao.equals("en")){ 
                               
                            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy"); 
                            Date dataCriacaoInicial = dateFormat.parse(filtroCampoDataCriacaoInicial);
                            Date dataCriacaoFinal = dateFormat.parse(filtroCampoDataCriacaoFinal);
                            cri.add(Restrictions.between("dataCriacao",dataCriacaoInicial,dataCriacaoFinal));
                                
                        }     
                       
                       if(filtroCampoAtivo!=null && !filtroCampoAtivo.isEmpty()){ 
                            cri.add(Restrictions.eq("ativo",filtroCampoAtivo));
                    
                        }

                               usuarios = new ArrayList();
                                usuarios = cri.list();
                                   usuarioSelecionado = null;
                                } catch(Exception e) {
  
                                      }
                         
      }
        
        
        public void onRowEdit(RowEditEvent event) {
            FacesMessage msg = new FacesMessage("Car Edited");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
         
        public void onRowCancel(RowEditEvent event) {
            FacesMessage msg = new FacesMessage("Edit Cancelled");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
         /*
        public void onCellEdit(CellEditEvent event) {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();
             
            if(newValue != null && !newValue.equals(oldValue)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
      
         */
}