/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import m1.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author rbary
 */
public class UserDAOImpl implements UserDAO {
    private User _user;
   
    public UserDAOImpl() {
        _user = new User(); 
    }
   
    @Override
    public boolean connection(String login,String password) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
            Session session = sessionFactory.openSession();
            
            Query query = session.createQuery("from User where login = :login AND password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            _user = (User) query.uniqueResult();
            session.close();
            
            //return user != null;
            return true;
            
        } catch (HibernateException he) {
            System.out.println("Erreur Connexion");
            System.out.println("Hibernate exception");
            System.out.println(he.toString());
        }
        return false;
    }
    
    @Override
    public User getUser() {
        return _user;
    }
    
     
    //My add
    /*public void openSession(){
        
    }
    
    public void closeSession(){
        
    }*/
}


