/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Util;
import com.sun.corba.se.impl.util.Utility;
import java.util.List;
import m1.entity.UserGroup;
import m1.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class UserGroupDAOImpl implements UserGroupDAO {

    public UserGroupDAOImpl() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<UserGroup> listUserGroups() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            /*Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());*/
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<UserGroup> userGroupList = null;
            try{
                userGroupList = session.createQuery("from UserGroup order by member_since desc").list();
                   
            }catch(Exception e){
                e.printStackTrace();
            }
            session.close();
            return userGroupList;
           
    } 
}
