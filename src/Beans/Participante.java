/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author flavio
 */

/*@Audited*/
@Entity
@Table(name="TB_PARTICIPANTE")
@SequenceGenerator(name = "idParticipante", sequenceName = "SEQ_PARTICIPANTE", initialValue=1, allocationSize=100) 
public class Participante implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="idParticipante") 
    @Column(name = "ID", nullable = false, length=10)
    private Integer id;
    
    @Column(name = "NOME_COMPLETO", nullable = false, length=20)
    private String nomeCompleto;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATA_NASCIMENTO", nullable = true)
    private Date dataNascimento;

    @Column(name = "SEXO", nullable = true, length=20)
    private char sexo;
    
    @Column(name = "EMAIL", nullable = true, length=20)
    private String email;
    
    @Column(name = "TELEFONE", nullable = true, length=20)
    private String telefone;
    
    @Column(name = "CIDADE", nullable = true, length=20)
    private String cidade;
    
    @OneToOne
    @JoinColumn(name = "ESCOLARIDADE_ID", nullable = false)
    private Escolaridade escolaridade;
    
    @Column(name = "SERIE", nullable = true, length=20)
    private String serie;
    
    @Column(name = "ESCOLA", nullable = true, length=20)
    private String escola;
    
    @Column(name = "FORMACAO", nullable = true, length=20)
    private String formacao;
    
    @Column(name = "AREA_ATUACAO", nullable = true, length=20)
    private String areaAtuacao;
    
    @Column(name = "INSTITUICAO", nullable = true, length=20)
    private String instituicao;
    
    @Column(name = "GRADUACAO", nullable = true, length=20)
    private String graduacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		return true;
	}

    
	
    
  
  
}
