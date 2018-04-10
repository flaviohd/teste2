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

/**
 *
 * @author flavio
 */
public class ParticipanteDao {
 
	private Session session; 
 
		public void inserir(Participante participante){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				session.beginTransaction();
				session.save(participante);
				session.getTransaction().commit();
 
			}finally{
				session.close();
 
			}
		}
 
		public void alterar(Participante participante){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.saveOrUpdate(participante);
				session.getTransaction().commit();
			}finally{
				session.close();
 
			}
		}
 
		public void excluir(Participante participante){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.delete(participante);
				session.getTransaction().commit();
 
			}finally{
				session.close();
 
			}
		}
 
		@SuppressWarnings("unchecked")
		public List  listar(){
			session = HibernateUtil.getSessionFactory().openSession();
 
			try{
				Criteria cri = session.createCriteria(Participante.class);
                               // System.out.println("Participante1 :");
                               //  cri.list().get(0).toString();
                               //  System.out.println("Participante 2:");
                               //  cri.list().get(1).toString();
				return cri.list();
			}finally{
				session.close();
 
			}
		}
}