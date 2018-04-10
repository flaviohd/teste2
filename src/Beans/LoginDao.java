/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import static Beans.Criptografia.geraCriptografia;
import DAO.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author flavio
 */
public class LoginDao {
     private static Session session;
    public static Usuario  validate(String usuario, String senha) throws Exception {
       // String senhaCriptografada = geraCriptografia(senha);
       // System.out.println("Senha Criptografada:" + senhaCriptografada);
        
        try{
              System.out.println("Antes HibernateUtil");
        session = HibernateUtil.getSessionFactory().openSession();
           System.out.println("Após HibernateUtil");
        
        }catch (Throwable e){
             System.out.println("Teste exception");
                 throw new Exception(e);
        }
        
         System.out.println("Teste exception2");
            /*
             FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,null,
               "Não foi possível estabelecer conexao com obanco de dados!!!") );
	    System.out.println("Não foi possível estabelecer conexao com obanco de dados!!!");
             
             session.close();
		}
        */
             // System.out.println("conexao realizada:" );
              
              
              
              
              
                 try{
	Criteria cri = session.createCriteria(Usuario.class);
               cri.add(Restrictions.eq("nomeUsuario",usuario));
          cri.add(Restrictions.eq("senha",senha));
        System.out.println("cri.list = " + cri.list().toString());
		if (!cri.list().isEmpty()){
                    System.out.println("cri.list = " + cri.list().get(0).toString());
                    
                    //return (Usuario) cri.list().get(0);
                    return null;
                }
		}finally{
				session.close();
		}
	
        
        
        /*Connection con = null;
        PreparedStatement ps = null;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
                */
        
        
        
       // return ((usuario.equals("admin")) && (senha.equals("admin123")));
                 return null;
    }
    
}