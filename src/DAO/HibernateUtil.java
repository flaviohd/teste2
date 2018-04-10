/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author flavio
 */
import Beans.Trabalho;
import Beans.Perfil;
import Beans.Categoria;
import Beans.Usuario;
import Beans.Participante;
import Beans.Trabalho;
import Beans.Escolaridade;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
 
private static SessionFactory sessionFactory;
 
private HibernateUtil() {
}
 
public static SessionFactory getSessionFactory() {
if (sessionFactory == null) {
try {
// Create the SessionFactory from standard (hibernate.cfg.xml)
// config file.
AnnotationConfiguration ac = new AnnotationConfiguration();
ac.addAnnotatedClass(Trabalho.class);
ac.addAnnotatedClass(Perfil.class);
ac.addAnnotatedClass(Categoria.class);
ac.addAnnotatedClass(Usuario.class);
ac.addAnnotatedClass(Participante.class);
ac.addAnnotatedClass(Trabalho.class);
ac.addAnnotatedClass(Escolaridade.class);



sessionFactory = ac.configure().buildSessionFactory();
} catch (Throwable ex) {
// Log the exception.
System.err.println("Initial SessionFactory creation failed." + ex);
throw new ExceptionInInitializerError(ex);
}
return sessionFactory;
} else {
return sessionFactory;
}

}
}
 /*
public class HibernateUtil {
 
    public static final SessionFactory session = buildSession();
 
    private static SessionFactory buildSession() {
 
    try{
         Configuration cfg = new Configuration();
                       cfg.configure("hibernate.cfg.xml");
 
                  return new AnnotationConfiguration().configure().buildSessionFactory();  
       // return cfg.buildSessionFactory();
 
       }catch(Throwable b){
 
            System.out.println("Não deu \n" + b);
            throw new ExceptionInInitializerError();
       }
   }
 
   public static SessionFactory getSessionFactory(){
       return session;
   }

}*/