package Beans;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



/**
 *
 * @author flavio
 */





@Entity
@Table(name="TB_TRABALHO")
@SequenceGenerator(name = "idTrabalho", sequenceName = "SEQ_TRABALHO", initialValue=1, allocationSize=100) 
public class Trabalho{
    
   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "NUMERO", nullable = false, length=10)
    private Integer numero;
    
	@Column(name = "TITULO", nullable = false, length=15)
    private String titulo;
	
	 @OneToOne
	 @JoinColumn(name = "CATEGORIA_ID", nullable = false)
	 private Categoria categoria;
	
	@Column(name = "AREA", nullable = false, length=15)
    private String area;
	
	@Column(name = "TEMA", nullable = false, length=15)
    private String tema;
	
	@Column(name = "RESUMO", nullable = false, length=15)
    private String resumo;
	
	@Column(name = "ORIENTADOR", nullable = false, length=15)
    private String orientador;
	
	@Column(name = "PALAVRA_CHAVE1", nullable = false, length=15)
    private String palavraChave1;
	
	@Column(name = "PALAVRA_CHAVE2", nullable = false, length=15)
    private String palavraChave2;
	
	@Column(name = "PALAVRA_CHAVE3", nullable = false, length=15)
    private String palavraChave3;
	
	 @OneToOne
	    @JoinColumn(name = "PARTICIPANTE_ID", nullable = false)
	    private Participante responsavel;
	
	

	@ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name="TB__TRABALHO_PARTICIPANTE", joinColumns={@JoinColumn(name="trabalho_id")}, inverseJoinColumns={@JoinColumn(name="participante_id")})
	    @Fetch (FetchMode.SELECT)
	    private Collection<Participante> participantes;
	
	
    public Trabalho() {
    	
    	System.out.println("inicio......");
    	responsavel = new Participante();
    	participantes = new ArrayList<Participante>();
    }

    public void adicionaParticipante(Participante participante) {
    	
    	participantes.add(participante);
    }
    
    public void removeParticipante(Participante participante) {
    	
    	participantes.remove(participante);
    }
    
    
    public Participante getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Participante responsavel) {
		this.responsavel = responsavel;
	}

	public Collection<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Collection<Participante> participantes) {
		this.participantes = participantes;
	}
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
    
    public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
    
    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    
    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }
    
    public String getPalavraChave1() {
        return palavraChave1;
    }

    public void setPalavraChave1(String palavraChave1) {
        this.palavraChave1 = palavraChave1;
    }
    
    public String getPalavraChave2() {
        return palavraChave2;
    }

    public void setPalavraChave2(String palavraChave2) {
        this.palavraChave2 = palavraChave2;
    }
    
    public String getPalavraChave3() {
        return palavraChave3;
    }

    public void setPalavraChave3(String palavraChave3) {
        this.palavraChave3 = palavraChave3;
    }

    
    
    
   
}



