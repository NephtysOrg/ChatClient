/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import m1.entity.Group;
import m1.entity.User;
import m1.entity.UserGroup;
import m1.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;



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
            Session session = HibernateUtil.getSessionFactory().openSession(); 
            Query query = session.createQuery("from User where login = :login AND password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            //_user = (User) query.uniqueResult();
            User user = (User)query.uniqueResult();
            this._user=user;
            session.close();
            
        return user != null;       
    }
    
    @Override
    public User getUser() {
        return _user;
    }
    
    @Override
    public List<Group> getMemberGroups(){ 
        Set<UserGroup> temp;
            List<Group> result = new ArrayList<>();
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from UserGroup where user_id = :id");
            query.setParameter("id", _user.getId());
            temp = new HashSet<>(query.list()); 
            Hibernate.initialize(temp);
            session.close();
            for(UserGroup tmp : temp){
                if(tmp.getSubscribed()+tmp.getInvited() == (byte)0 ){
                    result.add(tmp.getGroup());
                }
            }
            return result;
    }

    @Override
    public List<String> getMemberGroupsName() {
        List<Group> groups = this.getMemberGroups();
        List<String> result = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        for (Group tmp : groups) {
            result.add(tmp.getName());
        }
        session.close();
        return result;
    }
}


