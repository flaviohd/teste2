/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import DAO.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.event.SelectEvent;
import org.hibernate.Session;
/**
 *
 * @author flavio
 */


@ManagedBean(name="trabalhoExternoBean")
@ViewScoped
public class TrabalhoExternoBean implements Serializable{
 
	
	  
        public Trabalho trabalho;

	public TrabalhoExternoBean(){
		
		trabalho = new Trabalho(); 
		
		System.out.println("Número: " + trabalho.getNumero());
		System.out.println("Título: " + trabalho.getTitulo());
		System.out.println("Categoria: " + trabalho.getCategoria());
		//trabalho.setTitulo("teste");
		
        
	}
	

	
	 public void adicionaParticipante() {
	    	
		    Participante p = new Participante(); 
	    	trabalho.getParticipantes().add(p);
	    }
	    
	    public void removeParticipante(Participante participante) {
	    	trabalho.getParticipantes().remove(participante);
	    }
	  
	
    public Trabalho getTrabalho(){
    	return trabalho;
    }
    
    public void setTrabalho(Trabalho trabalho) {
    	this.trabalho = trabalho;
    }
    
	
        
    public void cadastrar(ActionEvent actionEvent) {
        
    	new ParticipanteDao().inserir(trabalho.getResponsavel());
	    new TrabalhoDao().inserir(trabalho);
	    
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "O seu trabalho foi enviado com sucesso!"));
        
	 /*
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registro incluído com sucesso.")); 
        FacesContext.getCurrentInstance().getExternalContext().redirect("portalCidadao.xhtml");*/
   }
   
                         
      
      
}