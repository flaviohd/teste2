/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Set;

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
import javax.persistence.Temporal;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
/*@Audited*/
@Table(name="TB_USUARIO")
@SequenceGenerator(name = "idUsuario", sequenceName = "SEQ_USUARIO", initialValue=1, allocationSize=100) 
public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="idUsuario") 
    @Column(name = "ID", nullable = false, length=10)
    private Integer id;
    
    @Column(name = "NOME_USUARIO", nullable = false, length=15, unique=true)
    private String nomeUsuario;
    
    @Column(name = "SENHA", nullable = false, length=100)
    private String senha;
    
    @Column(name = "NOME_COMPLETO", nullable = false, length=30)
    private String nomeCompleto;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "DATA_CADASTRO", nullable = false)
    private Date dataCriacao;
    
    @Column(name = "EMAIL", nullable = true, length=30)
    private String email;
    
    @Column(name = "ATIVO", nullable = false)
    private char ativo;
    
    @Column(name = "ALTERAR_SENHA", nullable = false)
    private char alterarSenha;
    
    @Column(name = "QTD_ACESSO_ERRO", nullable = false, length=1)
    private Integer qtdTentativasAcesso;
    
    @OneToOne
    @JoinColumn(name = "PERFIL_ID", nullable = false)
    private Perfil perfil;
    
    
   

    public Usuario(){
    	//departamentos = new Set<Departamento>;
    }
     
    public Integer getId(){
        return id;
    }
    
     public  void setId(Integer id){
        this.id = id;
    }   
	
	public  String getNomeUsuario(){
        return nomeUsuario;
    }
    
     public  void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }
     
    public  String getSenha(){
        return senha;
    }
    
     public  void setSenha(String senha){
        this.senha = senha;
    }
    
    public  String getNomeCompleto(){
        return nomeCompleto;
    }
    
     public  void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }
     
     public boolean validarNomeUsuario(){
        return true; 
     }
    
    public  Date getDataCriacao(){
        return dataCriacao;
    }
    
    public  String getDataFormatada(){

        return new SimpleDateFormat("dd/MM/YYYY").format(dataCriacao);
    }
    
    public  void setDataCriacao(Date dataCriacao){
        this.dataCriacao = dataCriacao;
    }
     
    public  String getEmail(){
        return email;
    }
    
    public  void setEmail(String email){
        this.email = email;
    }
     
    public  char getAtivo(){
        return ativo;
    }
    
    public String getDescricaoAtivo(){
        switch(ativo){
            case 'S':
            return "Sim";
            case 'N':
            return "N�o";
        }
        return "Sim";
    }
    
    public  void setAtivo(char ativo){
        this.ativo = ativo;
    }
     
     public  Perfil getPerfil(){
        return perfil;
    }
    
    public  void setPerfil(Perfil perfil){
        this.perfil = perfil;
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nomeUsuario=" + nomeUsuario + ", senha=" + senha + ", nomeCompleto=" + nomeCompleto + ", dataCriacao=" + dataCriacao + ", email=" + email + ", ativo=" + ativo + ", alterarSenha=" + alterarSenha + ", qtdTentativasAcesso=" + qtdTentativasAcesso + ", perfil=" + perfil + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nomeUsuario, other.nomeUsuario)) {
            return false;
        }
        return true;
    }

    public char getAlterarSenha() {
        return alterarSenha;
    }

    public void setAlterarSenha(char alterarSenha) {
        this.alterarSenha = alterarSenha;
    }

    public Integer getQtdTentativasAcesso() {
        return qtdTentativasAcesso;
    }

    public void setQtdTentativasAcesso(Integer qtdTentativasAcesso) {
        this.qtdTentativasAcesso = qtdTentativasAcesso;
    }  
    
}
