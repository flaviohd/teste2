/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author flavio
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import DAO.HibernateUtil;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author flavio
 */
public class UsuarioDao {
 
	private Session session; 
 
		public void inserir(Usuario usuario){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				session.beginTransaction();
				session.save(usuario);
				session.getTransaction().commit();
 
			}finally{
				session.close();
 
			}
		}
 
		public void alterar(Usuario usuario){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.saveOrUpdate(usuario);
				session.getTransaction().commit();
			}finally{
				session.close();
 
			}
		}
 
		public void excluir(Usuario usuario){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.delete(usuario);
				session.getTransaction().commit();
 
			}finally{
				session.close();
 
			}
		}
 
		@SuppressWarnings("unchecked")
		public List  listar(){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				Criteria cri = session.createCriteria(Usuario.class);
                         
				return cri.list();
			}finally{
				session.close();
 
			}
		}
                
                   public boolean verificaJaExiste(String nome) {
             
               Session session = HibernateUtil.getSessionFactory().openSession();
                     
			try{
                              
		        Criteria cri = session.createCriteria(Usuario.class);
                         cri.add(Restrictions.eq("nomeUsuario",nome));
                         
                         if(!cri.list().isEmpty())  
                             return true;
                        
                          } catch(Exception e){
                            
                          }      
             return false;
         }
}